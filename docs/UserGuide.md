# User Guide

Notenote is a desktop meeting note-taking application that allows users to efficiently record notes for their contact.
Notenote provide tools for organizing and categorizing contacts in a systematic and easy-to-navigate structure.

## Table of Contents

- [Contact Management](#contact-management)
    - [Create New Contact](#create-new-contact)
    - [View a Contact](#view-a-contact)
    - [List All Contacts](#list-all-contacts)
    - [Delete a Contact](#delete-a-contact)
- [Meeting Management](#meeting-management)
    - [Create a New Meeting](#create-a-new-meeting)
    - [View a Meeting](#view-a-meeting)
    - [List All Meetings](#list-all-meetings)
    - [Delete a Meeting](#delete-a-meeting)
    - [Edit a Meeting](#edit-a-meeting)
    - [Add Contact to Meeting](#add-contact-to-meeting)
    - [Delete Contact from Meeting](#delete-contact-from-meeting)
- [Note-Taking](#note-taking)
    - [Add Notes to a Contact or Meeting](#add-notes-to-a-contact-or-meeting)
    - [Delete Notes from a Contact or Meeting](#delete-notes-from-a-contact-or-meeting)
- [Miscellaneous](#miscellaneous)
    - [View List of Available Commands](#view-list-of-available-commands)

---

### Mode

- **What it does**: Toggles the mode of the application between "contacts" and "meetings". The application defaults to
  the "contacts" mode. The mode of the application determines the context in which the following commands are executed
  upon:
    1. add
    2. note
    3. list

- **Command Format**: `mode`

- **Expected Outputs**:
    - “Application mode set: [CURRENT_MODE].”

---

## Contact Management:

### Create New Contact

- **What it does**: Adds a new contact to the list when in the "contacts" mode.

- **Command Format**: `add n/CONTACT_NAME p/PHONE_NUMBER e/EMAIL_ADDRESS a/RESIDENTIAL_ADDRESS [t/TAGS]`

- **Example**: `add n/Sarah Woo p/82775346 e/sarah.woo@gmail.com a/Blk227 Sims Drive`

- **Acceptable Values**:
    - CONTACT_NAME: String, at least 2 characters long.

- **Expected Outputs**:
    - Success: “Successfully added [CONTACT_NAME].”
    - Failure:
        - If the CONTACT_NAME already exists: `This contact already exists in the address book`
        - If invalid command format: `Invalid command format!
          add: Adds a contact to the address book. Parameters: n/ NAME p/ PHONE e/ EMAIL a/ ADDRESS [t/ TAG]...
          Example: add n/ John Doe p/ 98765432 e/ johnd@example.com a/ 311, Clementi Ave 2, #02-25 t/ friends t/ owesMoney`
---

### View a Contact

- **What it does**: Displays details of a specific contact when in the "contacts" mode.

- **Command Format**: `view id/[CONTACT_ID]`

- **Example**: `view id/1`

- **Acceptable Values**:
    - CONTACT_ID: Non-negative integer.
    - CONTACT_NAME: String, at least 2 characters long. Not case sensitive.

- **Expected Outputs**:
    - Success: "Displaying details for [CONTACT_NAME]."
    - Failure:
        - If the CONTACT_ID or CONTACT_NAME does not exist: `The contact index provided is invalid`
        - If invalid command format: `Invalid command format!
          view: Shows the details of the contact identified by its id in the displayed contact list.
          Parameters: INDEX (must be a positive integer)
          Example: view id/ 1`

---

### List All Contacts

- **What it does**: Shows all contacts in the list when in the "contacts" mode. All fields after list are optional
  arguments.

- **Command Format**: `list n/[NAME] p/[PHONE] e/[EMAIL] a/[ADDRESS] t/[TAG] c/[NOTE]`

- **Expected Outputs**:
    - Success: `%d contacts listed`
    - Failure:
        - If no contacts are available: `No contacts available.`

---

### Editing A Contact

- **What it does**: Edits an existing person in the address book.

- **Command Format**: `edit id/INDEX n/CONTACT_NAME p/PHONE_NUMBER e/EMAIL_ADDRESS a/RESIDENTIAL_ADDRESS [t/TAGS]`

- **Example**: `edit id/3 p/90649923`
 
- **Expected Outputs**:
  - Success: "Edited Contact: [Details of CONTACT]"
  - Failure:
      - If the CONTACT_ID does not exist: `The contact index provided is invalid`
      - If no field to edit is specified: `At least one field to edit must be provided.`
      - If invalid command format: `Invalid command format!
        edit: Edits the details of the contact identified by the index number used in the displayed contact list. Existing values will be overwritten by the input values. Parameters: INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG] [c/NOTE]... Example: edit 1 p/91234567 -ejohndoe@example.com`

---

### Delete a Contact

- **What it does**: Removes a contact based on the given ID when in the "contacts" mode.

- **Command Format**: `delete id/[CONTACT_ID]`

- **Example**: `delete id/3`

- **Acceptable Values**:
    - CONTACT_ID: Non-negative integer.

- **Expected Outputs**:
    - Success: "Deleted Contact: [Details of CONTACT]."
    - Failure:
        - If the CONTACT_ID does not exist: `The contact index provided is invalid`
        - If the command is invalid: `Invalid command format!
          delete: Deletes the contact identified by the index number used in the displayed contact list.
          Parameters: INDEX (must be a positive integer)
          Example: delete id/ 1`

---

## Meeting Management:

### Create a New Meeting

- **What it does**: Organizes a new meeting with optional notes and contacts when in the "meetings" mode.

- **Command Format**: `add title/MEETING_NAME time/DD/MM/YYYY HH:MM place/OCATION [desc/DESCRIPTION]`

- **Example**: `add title/Project Discussion time/03/10/2023 15:00 place/Terrace desc/Discussing milestones`

- **Acceptable Values**:
    - MEETING_NAME: String, at least 2 characters long.
    - NOTES: String, at least 1 character long.
    - CONTACT_IDS: Non-negative integers delimited by commas.

- **Expected Outputs**:
    - Success: "[MEETING_NAME] successfully added."
    - Failure:
        - If the MEETING_NAME already exists: `Meeting already exists.`
        - If invalid command format: `Invalid command format`

---

### View a Meeting

- **What it does**: Displays details of a specific meeting when in the "meetings" mode.

- **Command Format**: `view id/[MEETING_ID]`

- **Example**: `view Project Discussion`

- **Acceptable Values**:
    - MEETING_ID: Non-negative integer.
    - MEETING_NAME: String, at least 2 characters long. Not case sensitive.

- **Expected Outputs**:
    - Success: "Displaying details for [MEETING_NAME]."
    - Failure:
        - If the MEETING_ID or MEETING_NAME does not exist: `Meeting not found`

---

### List All Meetings

- **What it does**: Shows a list of all meetings when in the "meetings" mode. All arguments after `list` are optional
  arguments.

- **Command Format**: `list title/[TITLE] time/[TIME] place/[PLACE] desc/[DESCRIPTION] m/[NOTE]`

- **Expected Outputs**:
    - Success: `%d Meetings Listed!`
    - Failure:
        - If no meetings are found: `No meetings found`
        - If invalid command format: `Invalid command format`

---

### Delete a Meeting

- **What it does**: Cancels a meeting based on the given ID or name when in the "meetings" mode.

- **Command Format**: `delete id/MEETING_ID`

- **Example**: `delete id/1`

- **Acceptable Values**:
    - MEETING_ID: Non-negative integer.
- **Expected Outputs**:
    - Success: "Successfully deleted [MEETING_NAME]."
    - Failure:
        - If the MEETING_ID does not exist: `The meeting index provided is invalid`
        - If the MEETING_ID is not provided OR If invalid command format: `Invalid command format`

---

### Edit A Meeting

- **What it does** Edits the details of an existing meeting in the address book.

- **Command Format**: `edit id/MEETING_ID [m/MEETING_NAME] [t/DD/MM/YYYY HH:MM] [p/LOCATION] [d/DESCRIPTION]`

- **Example**: `edit id/1 p/COM3`

- **Acceptable Values**:
    - MEETING_ID: Non-negative integer.
    - MEETING_NAME: String, at least 2 characters long.
- **Expected Outputs**:
    - Success: "Edited Meeting [Details of MEETING]."
    - Failure:
        - If the MEETING_ID does not exist: `The meeting index provided is invalid`
        - If invalid command format: `Invalid command format!
          edit: Edits the details of the meeting identified by the index number used in the displayed meeting list. Existing values will be overwritten by the input values. Parameters: INDEX (must be a positive integer) [title/TITLE] [time/TIME] [place/PLACE] [desc/DESCRIPTION]... Example: edit id/1 place/Zoom desc/Discuss Project Details`

---

### Add Contact to Meeting

- **What it does**: Adds a contact to an existing meeting as a participant when in the "meetings" mode.

- **Command Format**: `addcontact n/CONTACT_NAME m/MEETING_NAME`

- **Example**: `addcontact n/Sarah Woo m/Project Discussion`

- **Acceptable Values**:
    - MEETING_NAME: String, at least 2 characters long. Not case sensitive.
    - CONTACT_NAME: String, at least 2 characters long. Not case sensitive.

- **Expected Outputs**:
    - Success: "Added contact '[CONTACT_NAME]' to Meeting '[MEETING_NAME]'."
    - Failure:
        - If the MEETING_NAME does not exist: `The meeting specified is not created`
        - If the CONTACT_NAME does not exist: `The person specified is not created`
        - If invalid command format: `Invalid command format!
          add contact to meeting: Adds a contact to an existing meeting. Parameters: n/CONTACT_NAME title/MEETING_NAME Example: addcontact n/Sarah Woo title/Project Discussion`

---

### Delete Contact from Meeting

- **What it does**: Removes a contact from an existing meeting when in the "meetings" mode.

- **Command Format**: `deletecontact n/CONTACT_NAME m/MEETING_NAME`

- **Example**: `deletecontact n/Sarah Woo m/Project Discussion`

- **Acceptable Values**:
    - MEETING_NAME: String, at least 2 characters long. Not case sensitive.
    - CONTACT_NAME: String, at least 2 characters long. Not case sensitive.

- **Expected Outputs**:
    - Success: "Removed contact '[CONTACT_NAME]' from Meeting '[MEETING_NAME]'."
    - Failure:
        - If the MEETING_NAME does not exist: `The meeting specified is not created`
        - If the CONTACT_NAME does not exist or isn't a part of the specified
          meeting: `The person specified is not created`
        - If invalid command format: `Invalid command format!
          deletecontact : Removes a contact from an existing meeting. Parameters: n/CONTACT_NAME title/MEETING_NAME Example: deletecontact n/Sarah Woo title/Project Discussion`

---

## Note-Taking:

### Add Notes to a Contact or Meeting

- **What it does**: Associates notes with a specific contact or meeting

- **Command Format**:
    - For Contacts when in "contacts" mode: `addnote id/CONTACT_ID note/NOTES`
    - For Meetings when in "Meetings" mode: `addnote id/MEETING_ID note/NOTES`

- **Examples**:
    - `addnote id/5 note/Has a dog named Benny`
    - `addnote id/1 note/Agenda: Discuss Q2 results`

- **Acceptable Values**:
    - CONTACT_ID: Non-negative integer within range of Contact indexes.
    - MEETING_ID: Non-negative integer within range of Meeting indexes.
    - NOTES: String, at least 1 character long.

- **Expected Outputs**:
    - Success:
        - "Successfully added note to Contact: [Details of CONTACT]."
        - "Successfully added note to Meeting: [Details of MEETING]."
    - Failure:
        - If the CONTACT_ID or CONTACT_NAME does not exist: `The contact index provided is invalid`
        - If the MEETING_ID or MEETING_NAME does not exist: `The meeting index provided is invalid`
        - If the NOTES already exists for a given contact/meeting: `Error: the note already exists.`
        - If the NOTES aren't provided OR If invalid command format: `Invalid command format!
          addnote: Add notes to contact Parameters: id/CONTACT_ID_or_CONTACT_NAME note/NOTES Example: addnote id/5 note/ Has a dog named Benny`

### Delete Notes from a Contact or Meeting

- **What it does**: Removes specified notes from a contact or meeting.

- **Command Format**:
    - For Contacts when in "Contacts" mode: `deletenote id/CONTACT_ID noteid/NOTE_ID`
    - For Meetings when in "Meetings" mode: `deletenote id/MEETING_ID noteid/NOTE_ID`

- **Examples**:
    - `deletenote id/5 noteid/2`

- **Acceptable Values**:
    - CONTACT_ID: Non-negative integer within range of Contact indexes.
    - MEETING_ID: Non-negative integer within range of Meeting indexes.
    - NOTE_ID: Non-negative integer within range of noteIDs. ID of the note as displayed in the notes list of a contact or meeting.

- **Expected Outputs**:
    - Success:
        - "Removed note from Contact: [Details of CONTACT]."
        - "Removed note from Meeting: [Details of MEETING]."
    - Failure:
        - If the CONTACT_ID does not exist: `The contact index provided is invalid`
        - If the MEETING_ID does not exist: `The meeting index provided is invalid`
        - If no note exists for the specified note index in the contact or meeting OR If invalid command format: `Invalid command format!
          deletenote: Remove notes from contact Parameters: id/CONTACT_ID_or_CONTACT_NAME index/INDEX Example: deletenote id/5 noteid/1`
---

## Miscellaneous:

### View List of Available Commands

- **What it does**: Displays a list of available commands for the user.

- **Command Format**: `help`

- **Expected Outputs**:
    - Success: Displays a pop-up window with a link to the user guide.
