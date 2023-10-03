# User Guide

[![CI Status](https://github.com/AY2324S1-CS2103-W14-2/tp/workflows/Java%20CI/badge.svg)](https://github.com/se-edu/addressbook-level3/actions)

```
This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
```

![Ui](docs/images/Ui.png)

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

- **Command Format**: `contact -a CONTACT_NAME`

- **Example**: `contact -a Sarah`

- **Acceptable Values**:
    - CONTACT_NAME: String, at least 2 characters long.

- **Expected Outputs**:
    - Success: “Successfully added [CONTACT_NAME].”
    - Failure:
        - If the CONTACT_NAME already exists: “Error: Contact already exists.”
        - If invalid command format: "Error: Invalid command format."

---

### View a Contact

- **What it does**: Displays details of a specific contact.

- **Command Format**: `contact -v CONTACT_ID or CONTACT_NAME`

- **Example**: `contact -v Sarah`

- **Acceptable Values**:
    - CONTACT_ID: Non-negative integer.
    - CONTACT_NAME: String, at least 2 characters long. Not case sensitive.

- **Expected Outputs**:
    - Success: "Displaying details for [CONTACT_NAME]."
    - Failure:
        - If the CONTACT_ID or CONTACT_NAME does not exist: "Error: Contact not found."
        - If invalid command format: "Error: Invalid command format."

---

### List All Contacts

- **What it does**: Shows all contacts in the list.

- **Command Format**: `contact -l`

- **Expected Outputs**:
    - Success: List of all contacts.
    - Failure:
        - If no contacts are available: "Error: No contacts available."
        - If invalid command format: "Error: Invalid command format."

---

### Delete a Contact

- **What it does**: Removes a contact based on the given ID.

- **Command Format**: `contact -d CONTACT_ID`

- **Example**: `contact -d 3`

- **Acceptable Values**:
    - CONTACT_ID: Non-negative integer.

- **Expected Outputs**:
    - Success: "Successfully deleted [NAME of the contact] from the contact list."
    - Failure:
        - If the CONTACT_ID does not exist: "Error: Contact not found."
        - If the CONTACT_ID is not provided: "Error: Please provide a contact ID."
        - If invalid command format: "Error: Invalid command format."

---

## Meeting Management:

### Create a New Meeting

- **What it does**: Organizes a new meeting with optional notes and participants.

- **Command Format**: `meeting -a MEETING_NAME [-n NOTES] [-p CONTACT_IDS]`

- **Example**: `meeting -a "Project Discussion" -n "Discussing milestones" -p 2,5,7`

- **Acceptable Values**:
    - MEETING_NAME: String, at least 2 characters long.
    - NOTES: String, at least 1 character long.
    - CONTACT_IDS: Non-negative integers delimited by commas.

- **Expected Outputs**:
    - Success: "[MEETING_NAME] successfully added."
    - Failure:
        - If the MEETING_NAME already exists: "Error: Meeting already exists."
        - If any of the CONTACT_IDS don't exist: "Error: One or more participants not found."
        - If invalid command format: "Error: Invalid command format."

---

### View a Meeting

- **What it does**: Displays details of a specific meeting.

- **Command Format**: `meeting -v MEETING_ID or MEETING_NAME`

- **Example**: `meeting -v "Project Discussion"`

- **Acceptable Values**:
    - MEETING_ID: Non-negative integer.
    - MEETING_NAME: String, at least 2 characters long. Not case sensitive.

- **Expected Outputs**:
    - Success: "Displaying details for [MEETING_NAME]."
    - Failure:
        - If the MEETING_ID or MEETING_NAME does not exist: "Error: Meeting not found."
        - If invalid command format: "Error: Invalid command format."

---

### List All Meetings

- **What it does**: Shows a list of all meetings.

- **Command Format**: `meeting -l`

- **Expected Outputs**:
    - Success: List of all meetings.
    - Failure:
        - If no meetings are found: "Error: No meetings found."
        - If invalid command format: "Error: Invalid command format."

---

### Delete a Meeting

- **What it does**: Cancels a meeting based on the given ID or name.

- **Command Format**: `meeting -d MEETING_ID`

- **Example**: `meeting -d "Project Discussion"`

- **Acceptable Values**:
    - MEETING_ID: Non-negative integer.

- **Expected Outputs**:
    - Success: "Successfully deleted [MEETING_NAME]."
    - Failure:
        - If the MEETING_ID does not exist: "Error: Meeting not found."
        - If the MEETING_ID is not provided: "Error: Please specify a meeting."
        - If invalid command format: "Error: Invalid command format."

---

### Add Contact to Meeting

- **What it does**: Adds a contact to an existing meeting.

- **Command Format**: `meeting -am MEETING_ID or MEETING_NAME -p CONTACT_ID or CONTACT_NAME`

- **Example**: `meeting -am "Project Discussion" -p 5`

- **Acceptable Values**:
    - MEETING_ID: Non-negative integer.
    - MEETING_NAME: String, at least 2 characters long. Not case sensitive.
    - CONTACT_ID: Non-negative integer.
    - CONTACT_NAME: String, at least 2 characters long. Not case sensitive.

- **Expected Outputs**:
    - Success: "Successfully added contact to [MEETING_NAME]."
    - Failure:
        - If the MEETING_ID or MEETING_NAME does not exist: "Error: Meeting not found."
        - If the CONTACT_ID OR CONTACT_NAME does not exist: "Error: Contact not found."
        - If duplicate MEETING_NAME or CONTACT_NAME: "Error: Multiple matching names found."
        - If invalid command format: "Error: Invalid command format."

---

### Delete Contact from Meeting

- **What it does**: Removes a contact from an existing meeting.

- **Command Format**: `meeting -dm MEETING_ID or MEETING_NAME -p CONTACT_ID or CONTACT_NAME`

- **Example**: `meeting -dm "Project Discussion" -p 5`

- **Acceptable Values**:
    - MEETING_ID: Non-negative integer.
    - MEETING_NAME: String, at least 2 characters long. Not case sensitive.
    - CONTACT_ID: Non-negative integer.
    - CONTACT_NAME: String, at least 2 characters long. Not case sensitive.

- **Expected Outputs**:
    - Success: "Successfully removed contact from [MEETING_NAME]."
    - Failure:
        - If the MEETING_ID or MEETING_NAME does not exist: "Error: Meeting not found."
        - If duplicate MEETING_NAME or CONTACT_NAME: "Error: Multiple matching names found."
        - If the CONTACT_ID or CONTACT_NAME does not exist or isn't a part of the specified meeting: "Error: Contact not
          found or not part of the meeting."
        - If invalid command format: "Error: Invalid command format."

---

## Note-Taking:

### Add Notes to a Contact or Meeting

- **What it does**: Associates notes with a specific contact or meeting.

- **Command Format**:
    - For Contacts: `note -c CONTACT_ID or CONTACT_NAME -n NOTES`
    - For Meetings: `note -m MEETING_ID or MEETING_NAME -n NOTES`

- **Examples**:
    - `note -c 5 -n "Has a dog named Benny"`
    - `note -m "Project Discussion" -n "Agenda: Discuss Q2 results"`

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
        - If the CONTACT_ID or CONTACT_NAME does not exist: "Error: Contact not found."
        - If the MEETING_ID or MEETING_NAME does not exist: "Error: Meeting not found."
        - If duplicate MEETING_NAME or CONTACT_NAME: "Error: Multiple matching names found."
        - If the NOTES aren't provided: "Error: Please provide the note content."
        - If invalid command format: "Error: Invalid command format."

### Delete Notes from a Contact or Meeting

- **What it does**: Removes specified notes from a contact or meeting.

- **Command Format**:
    - For Contacts: `note -dc CONTACT_ID -i NOTE_INDEX`
    - For Meetings: `note -dm MEETING_ID or MEETING_NAME -i NOTE_INDEX`

- **Examples**:
    - `note -dc 5 -i 2`
    - `note -dm "Project Discussion" -i 1`

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
        - If the CONTACT_ID or CONTACT_NAME does not exist: "Error: Contact not found."
        - If the MEETING_ID or MEETING_NAME does not exist: "Error: Meeting not found."
        - If duplicate MEETING_NAME or CONTACT_NAME: "Error: Multiple matching names found."
        - If no note exists for the specified note index in the contact or meeting: "Error: No note found."
        - If invalid command format: "Error: Invalid command format."

---

## Miscellaneous:

### View List of Available Commands

- **What it does**: Displays a list of available commands for the user.

- **Command Format**: `help`

- **Expected Outputs**:
    - Success: Displays a pop-up window with a link to the user guide.
