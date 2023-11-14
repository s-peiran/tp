---
layout: page title: Jonathan Yeo's Project Portfolio Page
---

### Project: NoteNote

NoteNote is a desktop note-taking application that allows users to efficiently record notes for their contacts. It provides tools for organizing and categorizing contacts in a systematic and easy-to-navigate structure. Users interact with it through a CLI, and it has a GUI created with JavaFX. It is written in Java

Given below are my contributions to the project.

## Summary of Contributions

### Code Contributed

[Reposense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jonyeokj&breakdown=true)

### Enhancements Implemented

- **Storage Support for Meetings**: Developed the functionality to serialize the `Meeting` class instances to JSON and deserialize them back, ensuring persistent storage of meeting data across sessions.

- **Implemented Commands**: Implemented a suite of commands to enhance meeting and contact management, and added their equivalent tests:
    - `edit-meeting`: Allows users to modify details of existing meetings.
    - `delete-meeting`: Enables users to remove meetings that are no longer relevant.
    - `view-contact/meeting`: Provides a detailed view of a selected contact or meeting, facilitating better information access.

- **AppState Class**: Introduced an `AppState` class responsible for managing the application's state. It tracks and updates the UI components such as the list type and mode (contacts or meetings), and displays the specific contact or meeting being viewed.

- **Command History Feature**: Added a command history navigation feature, allowing users to cycle through their previous inputs using the up and down arrow keys. Included an autocomplete functionality which suggests previous commands that start with the entered prefix, enhancing the user experience with faster command input.

### Contributions to the UG

- **Quick Start and Tutorial**: Authored the Quick Start guide and the tutorial for new users, providing clear and concise instructions to enhance the onboarding experience for first-time users of NoteNote.

- **Command Summary and Acceptable Parameters**: Compiled the command summary and table of acceptable parameters, enabling users to quickly reference commands and their required inputs.

- **Proofreading**: Conducted thorough proofchecks of the User Guide to ensure accuracy and consistency, particularly after updates to the parser. This process was crucial to maintain the reliability of the documentation.

### Contributions to the DG

- **AppState Documentation**: Revised the architecture and UI component section to include the `AppState` class as well, describing its role as a singleton entity responsible for maintaining the dynamic state of the application. Additionally, documented the interaction between AppState and other main components.

- **Command History Documentation**: Crafted comprehensive documentation for the command history feature, including the rationale behind its implementation and the consideration of alternative solutions.

### Contributions to team-based tasks

- **Workflow Management**: Engineered the team's workflow, establishing agendas, and setting reminders for submissions to ensure project milestones were met in a timely manner.
