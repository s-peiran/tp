---
layout: page
title: Jonathan Yeo's Project Portfolio Page
---

### Project: NoteNote

NoteNote is a desktop note-taking application that allows users to efficiently record notes for their contacts. It provides tools for organizing and categorizing contacts in a systematic and easy-to-navigate structure. Users interact with it through a CLI, and it has a GUI created with JavaFX. It is written in Java

Given below are my contributions to the project.

## Summary of Contributions

### Code Contributed

[Reposense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jonyeokj&breakdown=true)

### Enhancement Implemented

- **Storage Support for Meetings**: Developed the functionality to serialize the `Meeting` class instances to JSON and deserialize them back, ensuring persistent storage of meeting data across sessions.

- **Implemented Commands**: Implemented a suite of commands to enhance meeting and contact management:
    - `edit-meeting`: Allows users to modify details of existing meetings.
    - `delete-meeting`: Enables users to remove meetings that are no longer relevant.
    - `view-contact/meeting`: Provides a detailed view of a selected contact or meeting, facilitating better information access.

- **AppState Class**: Introduced an `AppState` class responsible for managing the application's state. It tracks and updates the UI components such as the list type and mode (contacts or meetings), and displays the specific contact or meeting being viewed.

- **Command History Feature**: Added a command history navigation feature, allowing users to cycle through their previous inputs using the up and down arrow keys. Included an autocomplete functionality which suggests previous commands that start with the entered prefix, enhancing the user experience with faster command input.

### Contributions to the UG

- **Proofreading and Documentation**: Conducted thorough proofchecks of the User Guide to ensure accuracy and consistency, particularly after updates to the parser. This process was crucial to maintain the reliability of the documentation.

- **Error Documentation**: Identified and documented previously unreported or incorrectly reported errors.

### Contributions to the DG

- **Feature Documentation**: Added documentation on how the command history feature was planned to be implemented, as well as alternatives that we explored as well. 

### Contributions to team-based tasks

[TBC]
