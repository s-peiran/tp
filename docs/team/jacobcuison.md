---
layout: page
title: Jacob Cuison's Project Portfolio Page
---

### Project: NoteNote

NoteNote is a desktop note-taking application that allows users to efficiently record notes for their contacts. It provides tools for organizing and categorizing contacts in a systematic and easy-to-navigate structure. Users interact with it through a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

## Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jacobcuison&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

### Enhancements Implemented

  * Notes feature
    * Led implementation of Notes feature, which is the core functionality of the product. This included planning its integration with the rest of the app as well as the development itself.
    * **Commands** (dependent on the current Mode of the app)
      * `addnote`: adds notes to contacts/meetings
      * `deletenote`: deletes notes from contacts/meetings
    * Ensured proper behaviour of notes, including but not limited to:
      * Maintaining chronological order of notes on restarting the app
      * Ensuring indexing auto-updates upon addition/deletion of note
  * GUI improvements
    * Improved styling for contact, meeting, and notes details, as well as messages in `resultDisplay`
    * Ensured GUI remains readable and somewhat aesthetic with varying window sizes

### Contributions to the UG

* Added relevant user stories for NoteNote
* Added section on commands related to the Notes feature
* Proofread documentation multiple times and updated it where necessary to ensure reliability

### Contributions to the DG

* Documented details on the implementation of Notes feature, including the inner workings of the `addnote` and `deletenote` commands.
### Contributions to team-based tasks

* **Team-based Tasks**:
  * Refactored `Person` class and other associated classes to `Contact`. This involved updating the UML diagrams as well.
  * Created a nice logo and updated the product name in JavaFx
  * Assisted with reviewing PRs to ensure on-time milestone resolution
  * Assisted with tracking project deadlines and ensuring timely completion
  * Actively stress-tested the product and fixed bugs

