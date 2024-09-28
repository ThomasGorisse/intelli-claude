# intelli-claude

![Build](https://github.com/ThomasGorisse/intelli-claude/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Get familiar with the [template documentation][template].
- [ ] Adjust the [pluginGroup](./gradle.properties) and [pluginName](./gradle.properties), as well as the [id](./src/main/resources/META-INF/plugin.xml) and [sources package](./src/main/kotlin).
- [ ] Adjust the plugin description in `README` (see [Tips][docs:plugin-description])
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html?from=IJPluginTemplate).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the `MARKETPLACE_ID` in the above README badges. You can obtain it once the plugin is published to JetBrains Marketplace.
- [ ] Set the [Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate) related [secrets](https://github.com/JetBrains/intellij-platform-plugin-template#environment-variables).
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html?from=IJPluginTemplate).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

<!-- Plugin description -->

# IntelliClaude

IntelliClaude is an IntelliJ IDEA / Android Studio plugin that integrates Claude, an AI assistant created by Anthropic, directly into your development environment. With IntelliClaude, you can leverage the power of AI to assist with coding tasks, generate ideas, and enhance your productivity right from your IDE.

## Features

- **AI-Powered Code Assistance**: Get intelligent code suggestions and completions powered by Claude.
- **Natural Language Queries**: Ask Claude questions about your code or programming concepts in plain English.
- **Code Generation**: Generate code snippets or entire functions based on your descriptions.
- **Seamless Integration**: Works directly within your IntelliJ IDEA or Android Studio environment.

## Installation

1. Open IntelliJ IDEA or Android Studio.
2. Go to `File > Settings > Plugins` (on macOS: `IntelliJ IDEA > Preferences > Plugins`).
3. Click on "Browse repositories" and search for "IntelliClaude".
4. Click "Install" and restart your IDE when prompted.

Alternatively, you can download the plugin from the [JetBrains Plugin Repository](https://plugins.jetbrains.com/) and install it manually.

## Setup

1. After installation, you'll need to provide your Claude API key.
2. The plugin will prompt you for the API key on first use.
3. If you need to update your API key later, you can do so in the plugin settings.

To obtain a Claude API key:
1. Sign up for an account at [Anthropic's website](https://www.anthropic.com) or [Anthropic's Console](https://console.anthropic.com).
2. Navigate to the API section in your account dashboard.
3. Generate a new API key. Keep this key secure and do not share it publicly.

## Usage

1. Open a project in IntelliJ IDEA or Android Studio.
2. To use IntelliClaude, you can:
  - Use the "Ask Claude" action from the Edit menu.
  - Use the keyboard shortcut `Ctrl+Alt+C` (or `Cmd+Alt+C` on macOS).
  - Right-click in the editor and select "Ask Claude" from the context menu.
3. Enter your prompt or question when prompted.
4. Claude's response will be inserted at your cursor position in the active editor.

## Configuration

You can configure IntelliClaude in the IDE settings:
1. Go to `File > Settings > Tools > IntelliClaude` (on macOS: `IntelliJ IDEA > Preferences > Tools > IntelliClaude`).
2. Here you can update your API key and adjust other settings.

## Contributing

We welcome contributions to IntelliClaude! If you'd like to contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them with clear, descriptive messages.
4. Push your changes and submit a pull request.

Please ensure your code adheres to the existing style and includes appropriate tests.

## License

IntelliClaude is released under the MIT License. See the [LICENSE](LICENSE) file for details.

## Support

If you encounter any issues or have questions, please:
1. Check the [FAQ](FAQ.md) for common questions and answers.
2. If you can't find an answer, open an issue on the GitHub repository.

## Acknowledgements

IntelliClaude is built using the [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template) and integrates with Claude AI by Anthropic.

---

Thank you for using IntelliClaude! We hope it enhances your development experience and boosts your productivity.

This Fancy IntelliJ Platform Plugin is going to be your implementation of the brilliant ideas that you have.

This specific section is a source for the [plugin.xml](/src/main/resources/META-INF/plugin.xml) file which will be extracted by the [Gradle](/build.gradle.kts) during the build process.

To keep everything working, do not remove `<!-- ... -->` sections. 
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "intelli-claude"</kbd> >
  <kbd>Install</kbd>
  
- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/ThomasGorisse/intelli-claude/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
