# User Guide

Notenote
is a desktop meeting note-taking application that allows users to efficiently record notes for their contact.
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
    - [Add Contact to Meeting](#add-contact-to-meeting)
    - [Delete Contact from Meeting](#delete-contact-from-meeting)
- [Note-Taking](#note-taking)
    - [Add Notes to a Contact or Meeting](#add-notes-to-a-contact-or-meeting)
    - [Delete Notes from a Contact or Meeting](#delete-notes-from-a-contact-or-meeting)
- [Miscellaneous](#miscellaneous)
    - [View List of Available Commands](#view-list-of-available-commands)

---

## Contact Management:

### Create New Contact

- **What it does**: Adds a new contact to the list.

- **Command Format**: `addc -n CONTACT_NAME -p PHONE_NUMBER -e EMAIL_ADDRESS -a RESIDENTIAL_ADDRESS [-t TAGS]`

- **Example**: `addc -n Sarah Woo -p 82775346 -e sarah.woo@gmail.com -a Blk227 Sims Drive`

- **Acceptable Values**:
    - CONTACT_NAME: String, at least 2 characters long.

- **Expected Outputs**:
    - Success: “Successfully added [CONTACT_NAME].”
    - Failure:
        - If the CONTACT_NAME already exists: `This contact already exists in the address book`
        - If invalid command format: `Invalid command format!
          addc: Adds a contact to the address book. Parameters: -n NAME -p PHONE -e EMAIL -a ADDRESS [-tTAG]...
          Example: addc -n John Doe -p 98765432 -e johnd@example.com -a 311, Clementi Ave 2, #02-25 -t friends -t owesMoney`

---

### View a Contact

- **What it does**: Displays details of a specific contact.

- **Command Format**: `viewc -[CONTACT_ID or CONTACT_NAME]`

- **Example**: `viewc -Sarah`

- **Acceptable Values**:
    - CONTACT_ID: Non-negative integer.
    - CONTACT_NAME: String, at least 2 characters long. Not case sensitive.

- **Expected Outputs**:
    - Success: "Displaying details for [CONTACT_NAME]."
    - Failure:
        - If the CONTACT_ID or CONTACT_NAME does not exist: `Contact not found!`
        - If invalid command
          format: `Invalid command format!          viewc: View a contact to the address book. Parameters: -[CONTACT_ID or CONTACT_NAME]
          Example: viewc -Sarah`

---

### List All Contacts

- **What it does**: Shows all contacts in the list.

- **Command Format**: `listc`

- **Expected Outputs**:
    - Success: List of all contacts.
    - Failure:
        - If no contacts are available: `No contacts available.`

---

### Delete a Contact

- **What it does**: Removes a contact based on the given ID.

- **Command Format**: `delc [CONTACT_ID]`

- **Example**: `delc 3`

- **Acceptable Values**:
    - CONTACT_ID: Non-negative integer.

- **Expected Outputs**:
    - Success: "Successfully deleted [NAME of the contact] from the contact list."
    - Failure:
        - If the CONTACT_ID does not exist: `Contact not found.`
        - If the CONTACT_ID is not provided: `Please provide a contact ID.`

---

## Meeting Management:

### Create a New Meeting

- **What it does**: Organizes a new meeting with optional notes and contacts.

- **Command
  Format**: `addm -title MEETING_NAME -time DD/MM/YYYY HH:MM -place LOCATION[-desc DESCRIPTION]`

- **Example**: `addm -title Project Discussion -time 03/10/2023 15:00 -place Terrace -desc Discussing milestones`

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

- **What it does**: Displays details of a specific meeting.

- **Command Format**: `viewm [MEETING_ID or MEETING_NAME]`

- **Example**: `viewm Project Discussion`

- **Acceptable Values**:
    - MEETING_ID: Non-negative integer.
    - MEETING_NAME: String, at least 2 characters long. Not case sensitive.

- **Expected Outputs**:
    - Success: "Displaying details for [MEETING_NAME]."
    - Failure:
        - If the MEETING_ID or MEETING_NAME does not exist: `Meeting not found`

---

### List All Meetings

- **What it does**: Shows a list of all meetings.

- **Command Format**: `listm`

- **Expected Outputs**:
    - Success: List of all meetings.
    - Failure:
        - If no meetings are found: `No meetings found`
        - If invalid command format: `Invalid command format`

---

### Delete a Meeting

- **What it does**: Cancels a meeting based on the given ID or name.

- **Command Format**: `delm MEETING_ID`

- **Example**: `delm Project Discussion`

- **Acceptable Values**:
    - MEETING_ID: Non-negative integer.
    - MEETING_NAME: String, at least 2 characters long.
- **Expected Outputs**:
    - Success: "Successfully deleted [MEETING_NAME]."
    - Failure:
        - If the MEETING_ID does not exist: `Meeting not found`
        - If the MEETING_ID is not provided: `Please specify a meeting`
        - If invalid command format: `Invalid command format`

---

### Add Contact to Meeting

- **What it does**: Adds a contact to an existing meeting as a participant.

- **Command Format**: `addctm -n CONTACT_NAME -title MEETING_NAME`

- **Example**: `addctm -n Sarah Woo -title Project Discussion`

- **Acceptable Values**:
    - MEETING_NAME: String, at least 2 characters long. Not case sensitive.
    - CONTACT_NAME: String, at least 2 characters long. Not case sensitive.

- **Expected Outputs**:
    - Success: "Successfully added contact to [MEETING_NAME]."
    - Failure:
        - If the MEETING_NAME does not exist: `Meeting not found`
        - If the CONTACT_NAME does not exist: `Contact not found`
        - If invalid command format: `Invalid command format!
          addctm: Adds a contact to an existing meeting. Parameters: -n CONTACT_NAME -title MEETING_NAME
          Example: addctm -n Sarah Woo -title Project Discussion`

---

### Delete Contact from Meeting

- **What it does**: Removes a contact from an existing meeting.

- **Command Format**: `delcfm -n CONTACT_NAME -title MEETING_NAME`

- **Example**: `delcfm -n Sarah Woo -title Project Discussion`

- **Acceptable Values**:
    - MEETING_NAME: String, at least 2 characters long. Not case sensitive.
    - CONTACT_NAME: String, at least 2 characters long. Not case sensitive.

- **Expected Outputs**:
    - Success: "Successfully removed contact from [MEETING_NAME]."
    - Failure:
        - If the MEETING_NAME does not exist: `Meeting not found`
        - If the CONTACT_NAME does not exist or isn't a part of the specified meeting: `Contact not
          found or not part of the meeting`
        - If invalid command format: `Invalid command format!
          delcfm: Removes a contact from an existing meeting. Parameters: -n CONTACT_NAME -title MEETING_NAME
          Example: delcfm -n Sarah Woo -title Project Discussion`

---

## Note-Taking:

### Add Notes to a Contact or Meeting

- **What it does**: Associates notes with a specific contact or meeting.

- **Command Format**:
    - For Contacts: `addnotec -id CONTACT_ID_or_CONTACT_NAME -note NOTES`
    - For Meetings: `addnotem -id MEETING_ID_or_MEETING_NAME -note NOTES`

- **Examples**:
    - `addnotec -id 5 -note Has a dog named Benny`
    - `addnotem -id Project Discussion -note Agenda: Discuss Q2 results`

- **Acceptable Values**:
    - CONTACT_ID: Non-negative integer.
    - CONTACT_NAME: String, at least 2 characters long. Not case sensitive.
    - MEETING_ID: Non-negative integer.
    - MEETING_NAME: String, at least 2 characters long. Not case sensitive.
    - NOTES: String, at least 1 character long.

- **Expected Outputs**:
    - Success:
        - "Successfully added note to contact [CONTACT_NAME]."
        - "Successfully added note to [MEETING_NAME]."
    - Failure:
        - If the CONTACT_ID or CONTACT_NAME does not exist: `Contact not found`
        - If the MEETING_ID or MEETING_NAME does not exist: `Meeting not found`
        - If the NOTES aren't provided:  `Please provide the note content`
        - If invalid command format: `Invalid command format!
          addnotec: Add notes to contact Parameters: -id CONTACT_ID_or_CONTACT_NAME -note NOTES
          Example: addnotec -id 5 -note Has a dog named Benny`

### Delete Notes from a Contact or Meeting

- **What it does**: Removes specified notes from a contact or meeting.

- **Command Format**:
    - For Contacts: `delnotec -id CONTACT_ID_or_CONTACT_NAME -index NOTE_INDEX`
    - For Meetings: `delnotem -id MEETING_ID_or_MEETING_NAME -index NOTE_INDEX`

- **Examples**:
    - `delnotec -id 5 -index 2`
    - `delnotem -id Project Discussion -index 1`

- **Acceptable Values**:
    - CONTACT_ID: Non-negative integer.
    - CONTACT_NAME: String, at least 2 characters long. Not case sensitive.
    - MEETING_ID: Non-negative integer.
    - MEETING_NAME: String, at least 2 characters long. Not case sensitive.
    - NOTE_INDEX: Non-negative integer. Index of the note as displayed in the notes list of a contact or meeting.

- **Expected Outputs**:
    - Success:
        - "Successfully deleted note from contact [CONTACT_NAME]."
        - "Successfully deleted note from [MEETING_NAME]."
    - Failure:
        - If the CONTACT_ID or CONTACT_NAME does not exist: `Contact not found`
        - If the MEETING_ID or MEETING_NAME does not exist: `Meeting not found`
        - If no note exists for the specified note index in the contact or meeting: `No note found`
        - If invalid command format: `Invalid command format!
          delnotec: Remove notes from contact Parameters: -id CONTACT_ID_or_CONTACT_NAME -index INDEX
          Example: delnotec -id 5 -index 1`

---

## Miscellaneous:

### View List of Available Commands

- **What it does**: Displays a list of available commands for the user.

- **Command Format**: `help`

- **Expected Outputs**:
    - Success: Displays a pop-up window with a link to the user guide.
