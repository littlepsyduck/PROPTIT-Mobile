package controller

import model.Contact
import view.ContactView
import java.util.Scanner

class ContactController {
    private val view: ContactView = ContactView(this)
    private val scanner = Scanner(System.`in`)
    private val contacts = mutableListOf<Contact>()
    private var nextId = 1
    fun start() {
        while (true) {
            view.displayMenu()
            when (scanner.nextInt()) {
                1 -> view.addContact()
                2 -> view.editContact()
                3 -> view.deleteContact()
                4 -> view.viewContacts()
                5 -> {
                    view.displayMessage("Exiting...")
                    return
                }
                else -> view.displayMessage("Invalid option. Please try again.")
            }
        }
    }

    fun addContact(name: String, phone: String) {
        contacts.add(Contact(nextId++, name, phone))
        view.displayMessage("Contact added successfully.")
    }

    fun editContact(id: Int, newName: String, newPhone: String) {
        val contact = contacts.find { it.id == id }
        if (contact != null) {
            contact.name = newName
            contact.phone = newPhone
            view.displayMessage("Contact updated successfully.")
        } else {
            view.displayMessage("Contact not found.")
        }
    }

    fun deleteContact(id: Int) {
        val contact = contacts.find { it.id == id }
        if (contact != null) {
            contacts.remove(contact)
            view.displayMessage("Contact deleted successfully.")
        } else {
            view.displayMessage("Contact not found.")
        }
    }

    fun viewContacts(): List<Contact> {
        return contacts
    }
}
