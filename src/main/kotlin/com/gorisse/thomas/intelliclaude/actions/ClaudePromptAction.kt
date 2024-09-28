package com.gorisse.thomas.intelliclaude.actions

import com.gorisse.thomas.intelliclaude.client.ClaudeApiClient
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import kotlinx.coroutines.runBlocking

class ClaudePromptAction : AnAction("Ask Claude") {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return

        val prompt = Messages.showInputDialog(
            project,
            "Enter your prompt for Claude:",
            "Claude Prompt",
            Messages.getQuestionIcon()
        ) ?: return

        val client = ClaudeApiClient.getInstance(project)

        runBlocking {
            try {
                val response = client.queryClaude(prompt)
                insertResponseIntoEditor(project, editor, response)
            } catch (e: Exception) {
                Messages.showErrorDialog(
                    project,
                    "Error querying Claude: ${e.message}",
                    "Claude API Error"
                )
            }
        }
    }

    private fun insertResponseIntoEditor(project: Project, editor: Editor, response: String) {
        WriteCommandAction.runWriteCommandAction(project) {
            val document = editor.document
            val caretModel = editor.caretModel
            val offset = caretModel.offset
            document.insertString(offset, response)
            caretModel.moveToOffset(offset + response.length)
        }
    }
}