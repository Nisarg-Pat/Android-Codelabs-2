/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.infinityapps.credentialmanager.sample

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.GetPasswordOption
import androidx.credentials.GetPublicKeyCredentialOption
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.NoCredentialException
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.infinityapps.credentialmanager.sample.databinding.FragmentSignInBinding
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {

    private lateinit var credentialManager: CredentialManager
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener: SignInFragmentCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as SignInFragmentCallback
        } catch (castException: ClassCastException) {
            /** The activity does not implement the listener.  */
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        credentialManager = CredentialManager.create(requireActivity())

        binding.signInWithSavedCredentials.setOnClickListener(signInWithSavedCredentials())
        binding.signInWithGoogle.setOnClickListener(signInWithGoogle())
    }

    private fun signInWithSavedCredentials(): View.OnClickListener {
        return View.OnClickListener {

            lifecycleScope.launch {
                configureViews(View.VISIBLE, false)

                //Call getSavedCredentials() method to signin using passkey/password
                val data = getSavedCredentials()

                configureViews(View.INVISIBLE, true)

                //Complete the authentication process after validating the public key credential to your server and let the user in.
                data?.let {
                    sendSignInResponseToServer()
                    listener.showHome()
                }
            }
        }
    }

    private fun signInWithGoogle(): View.OnClickListener {
        return View.OnClickListener {
            lifecycleScope.launch {
                configureViews(View.VISIBLE, false)

                val data = getSavedGoogleCredentials()
                Log.d("Auth", data ?: "null")

                configureViews(View.INVISIBLE, true)

                //Complete the authentication process after validating the public key credential to your server and let the user in.
                data?.let {
                    sendSignInResponseToServer()
                    listener.showHome()
                }
            }
        }
    }

    private fun configureViews(visibility: Int, flag: Boolean) {
        configureProgress(visibility)
        binding.signInWithSavedCredentials.isEnabled = flag
        binding.signInWithGoogle.isEnabled = flag
    }

    private fun configureProgress(visibility: Int) {
        binding.textProgress.visibility = visibility
        binding.circularProgressIndicator.visibility = visibility
    }

    private fun fetchAuthJsonFromServer(): String {
        //Fetch authentication mock json
        return requireContext().readFromAsset("AuthFromServer")
    }

    private fun sendSignInResponseToServer(): Boolean {
        return true
    }

    private suspend fun getSavedCredentials(): String? {

        //Create a GetPublicKeyCredentialOption() with necessary authentication json from server
        val getPublicKeyCredentialOption =
            GetPublicKeyCredentialOption(fetchAuthJsonFromServer(), null)

        //Create a PasswordOption to retrieve all the associated user's password
        val getPasswordOption = GetPasswordOption()

        //Call getCredential() with required credential options
        val result = try {
            credentialManager.getCredential(
                requireActivity(),
                GetCredentialRequest(
                    listOf(
                        getPublicKeyCredentialOption,
                        getPasswordOption
                    )
                )
            )
        } catch (e: Exception) {
            configureViews(View.INVISIBLE, true)
            Log.e("Auth", "getCredential failed with exception: " + e.message.toString())
            activity?.showErrorAlert(
                "An error occurred while authenticating through saved credentials. Check logs for additional details"
            )
            return null
        }

        if (result.credential is PublicKeyCredential) {
            val cred = result.credential as PublicKeyCredential
            DataProvider.setSignedInThroughPasskeys(true)
            return "Passkey: ${cred.authenticationResponseJson}"
        }
        if (result.credential is PasswordCredential) {
            val cred = result.credential as PasswordCredential
            DataProvider.setSignedInThroughPasskeys(false)
            return "Got Password - User:${cred.id} Password:${cred.password}"
        }
        if (result.credential is CustomCredential) {
            //If you are also using any external sign-in libraries, parse them here with the utility functions provided.
        }

        return null
    }

    private suspend fun getSavedGoogleCredentials(): String? {

        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId("348542075961-27pvb7t08v209k64r1lflnu07r383fe0.apps.googleusercontent.com")
            .setAutoSelectEnabled(true)
            .setNonce("N7jd8Wl2zYErgT45BQm_UA")
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            val result = credentialManager.getCredential(
                requireActivity(),
                request
            )
            return handleGoogleSignIn(result)

        } catch (e: Exception) {
            return handleGoogleSignInFailure(e)
        }
    }

    private suspend fun createGoogleCredentials(): String? {

        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId("348542075961-27pvb7t08v209k64r1lflnu07r383fe0.apps.googleusercontent.com")
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            val result = credentialManager.getCredential(
                requireActivity(),
                request
            )
            return handleGoogleSignIn(result)

        } catch (e: Exception) {
            configureViews(View.INVISIBLE, true)
            Log.e("Auth", "getCredential failed with exception: " + e.message.toString())
            activity?.showErrorAlert(
                "An error occurred while authenticating through saved credentials. Check logs for additional details"
            )
        }
        return null
    }

    fun handleGoogleSignIn(result: GetCredentialResponse): String? {
        // Handle the successfully returned credential.
        val credential = result.credential

        when (credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {

                    try {
                        // Use googleIdTokenCredential and extract id to validate and
                        // authenticate on your server.
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)
                        return "Got Sign In - User:${googleIdTokenCredential.displayName}"
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e("Auth", "Received an invalid google id token response", e)
                    }
                } else {
                    // Catch any unrecognized credential type here.
                    Log.e("Auth", "Unexpected type of credential")
                }
            }

            else -> {
                // Catch any unrecognized credential type here.
                Log.e("Auth", "Unexpected type of credential")
            }
        }

        return null
    }

    suspend fun handleGoogleSignInFailure(e: Exception): String? {
        if (e is NoCredentialException) {
            return createGoogleCredentials()
        } else {
            configureViews(View.INVISIBLE, true)
            Log.e("Auth", "getCredential failed with exception: " + e.message.toString())
            activity?.showErrorAlert(
                "An error occurred while authenticating through saved credentials. Check logs for additional details"
            )
        }
        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        configureProgress(View.INVISIBLE)
        _binding = null
    }

    interface SignInFragmentCallback {
        fun showHome()
    }
}
