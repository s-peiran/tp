# User Guide

---

## Table of Contents

- [Contact Management](#contact-management)
  - [Add a Contact](#add-a-contact)
  - [View Contacts](#view-contacts)
  - [Delete a Contact](#delete-a-contact)
- [Tagging and Categorization](#tagging-and-categorization)
  - [Tag a Contact](#tag-a-contact)
- [Note-Taking](#note-taking)
  - [Add Notes to Contacts](#add-notes-to-contacts)
  - [Add Notes to Tags](#add-notes-to-tags)
- [Miscellaneous](#miscellaneous)
  - [View Commands](#view-commands)

---

## Contact Management

### Add a Contact

- **What it does:** Adds a new contact to the application.
  
- **Command format:** `add contact NAME PHONE EMAIL`
  
- **Example:** `add contact John Doe 123456789 john@example.com`
  
- **Acceptable values:**
  - **NAME:** Any combination of letters and spaces. Must be at least two characters long.
  - **PHONE:** Any 9-digit number.
  - **EMAIL:** A valid email format (e.g., `name@domain.com`).
  
- **Expected outputs (success):** A message confirming the contact has been added and details of the new contact.
  
- **Expected outputs (failure):** 
  - If the NAME, PHONE, or EMAIL is missing: "Error: Missing [parameter]. Please provide [parameter]."
  - If PHONE is not a 9-digit number: "Error: Invalid phone number format."
  - If EMAIL is not in a valid format: "Error: Invalid email format."

### View Contacts

- **What it does:** Displays a list of all contacts.
  
- **Command format:** `view contacts`
  
- **Example:** `view contacts`
  
- **Expected outputs (success):** A list of all saved contacts with their details.
  
- **Expected outputs (failure):** "Error: No contacts found."

### Delete a Contact

- **What it does:** Removes a contact based on the given ID.
  
- **Command format:** `delete contact CONTACT_ID`
  
- **Example:** `delete contact 3`
  
- **Acceptable values:**
  - **CONTACT_ID:** A valid number corresponding to a contact.
  
- **Expected outputs (success):** "Successfully deleted [NAME of the contact]."
  
- **Expected outputs (failure):**
  - If the CONTACT_ID does not exist: "Error: Contact not found."
  - If the CONTACT_ID is not provided: "Error: Please provide a contact ID."

---

## Tagging and Categorization

### Tag a Contact

- **What it does:** Tags a specific contact with a given tag name.
  
- **Command format:** `tag contact CONTACT_ID TAG_NAME`
  
- **Example:** `tag contact 2 Math`
  
- **Acceptable values:**
  - **CONTACT_ID:** A valid number corresponding to a contact.
  - **TAG_NAME:** Any combination of letters and spaces. Must be at least two characters long.
  
- **Expected outputs (success):** "Successfully tagged [NAME of the contact] with [TAG_NAME]."
  
- **Expected outputs (failure):**
  - If the CONTACT_ID does not exist: "Error: Contact not found."
  - If the TAG_NAME is not provided: "Error: Please provide a tag name."

---

## Note-Taking

### Add Notes to Contacts

- **What it does:** Adds a note to a specific contact.
  
- **Command format:** `add note CONTACT_ID NOTE_CONTENT`
  
- **Example:** `add note 1 Had a meeting on Monday.`
  
- **Acceptable values:**
  - **CONTACT_ID:** A valid number corresponding to a contact.
  - **NOTE_CONTENT:** Any text up to 500 characters.
  
- **Expected outputs (success):** "Successfully added note to [NAME of the contact]."
  
- **Expected outputs (failure):** 
  - If the CONTACT_ID does not exist: "Error: Contact not found."
  - If the NOTE_CONTENT is not provided: "Error: Please provide note content."

### Add Notes to Tags

- **What it does:** Adds a note to a specific tag.
  
- **Command format:** `add tag-note TAG_NAME NOTE_CONTENT`
  
- **Example:** `add tag-note Math Review next week's lesson.`
  
- **Acceptable values:**
  - **TAG_NAME:** Any combination of letters and spaces. Must be at least two characters long.
  - **NOTE_CONTENT:** Any text up to 500 characters.
  
- **Expected outputs (success):** "Successfully added note to [TAG_NAME] tag."
  
- **Expected outputs (failure):**
  - If the TAG_NAME does not exist: "Error: Tag not found."
  - If the NOTE_CONTENT is not provided: "Error: Please provide note content."

---

## Miscellaneous

### View Commands

- **What it does:** Shows a list of all available commands.
  
- **Command format:** `help`
  
- **Example:** `help`
  
- **Expected outputs (success):** A list of all commands with brief descriptions.
  
- **Expected outputs (failure):** N/A

---

