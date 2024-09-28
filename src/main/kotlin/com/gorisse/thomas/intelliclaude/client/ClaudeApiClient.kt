package com.gorisse.thomas.intelliclaude.client

import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.Credentials
import com.intellij.ide.BrowserUtil
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.InputValidator
import com.intellij.openapi.ui.Messages
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@Service(Service.Level.PROJECT)
class ClaudeApiClient(private val project: Project) {
    private val client = HttpClient(Apache) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    private fun getApiKey(): String {
        val credentialAttributes = CredentialAttributes("IntelliClaude.ApiKey")
        var credentials = PasswordSafe.instance.get(credentialAttributes)

        if (credentials == null || credentials.getPasswordAsString().isNullOrEmpty()) {
            val instructions = """
                To use IntelliClaude, you need a Claude API key from Anthropic. Here's how to get one:

                1. Visit https://www.anthropic.com or https://console.anthropic.com
                2. Sign up for an account or log in if you already have one
                3. Navigate to the API section in your account dashboard
                4. Generate a new API key
                5. Copy the key and paste it below

                Note: Keep your API key secure and don't share it publicly.

                For more detailed instructions, please refer to the 'API Key Setup' section in our README.
                Click 'Open README' for more information.

                Please enter your Claude API key:
            """.trimIndent()

            val readmeUrl =
                "https://github.com/yourusername/intelli-claude/blob/main/README.md#api-key-setup"

            val apiKey = Messages.showInputDialog(
                project,
                instructions,
                "Claude API Key Required",
                Messages.getQuestionIcon(),
                null,
                object : InputValidator {
                    override fun checkInput(inputString: String): Boolean = inputString.isNotBlank()
                    override fun canClose(inputString: String): Boolean = true
                },
                null
            )

            if (apiKey.isNullOrBlank()) {
                throw IllegalStateException("API key is required to use Claude")
            }

            credentials = Credentials("Claude API Key", apiKey)
            PasswordSafe.instance.set(credentialAttributes, credentials)

            // Show a message with the option to open the README
            val openReadme = Messages.showYesNoDialog(
                project,
                "API key saved. Would you like to open the README for more information?",
                "API Key Saved",
                "Open README",
                "Close",
                Messages.getInformationIcon()
            )

            if (openReadme == Messages.YES) {
                BrowserUtil.browse(readmeUrl)
            }
        }

        return credentials.getPasswordAsString()
            ?: throw IllegalStateException("Failed to retrieve API key")
    }

    suspend fun queryClaude(prompt: String): String {
        val apiKey = getApiKey()
        val response: HttpResponse = client.post("https://api.anthropic.com/v1/completions") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer $apiKey")
            setBody(ClaudeRequest(prompt = prompt))
        }

        val responseBody = response.bodyAsText()
        // Parse the response body to extract the completion
        // You might want to use a JSON parsing library here
        // For simplicity, let's assume the response is in the format: {"completion": "..."}
        return responseBody.substringAfter("\"completion\":").trim().removeSurrounding("\"")
    }

    @kotlinx.serialization.Serializable
    data class ClaudeRequest(
        val prompt: String,
        val model: String = "claude-2.1",
        val max_tokens_to_sample: Int = 1000
    )

    companion object {
        fun getInstance(project: Project): ClaudeApiClient {
            return project.service()
        }
    }
}