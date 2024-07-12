package view

import controller.ContactController
import model.Contact
import java.util.*

class ContactView (private val controller: ContactController) {
    private val scanner = Scanner(System.`in`)
    fun displayMenu() {
        println("\n--- Contact Manager ---")
        println("1. Add Contact")
        println("2. Edit Contact")
        println("3. Delete Contact")
        println("4. View Contacts")
        println("5. Exit")
        print("Choose an option: ")
    }

    fun addContact() {
        displayMessage("Enter name: ")
        val name = scanner.next()
        displayMessage("Enter phone: ")
        val phone = scanner.next()
        controller.addContact(name, phone)
    }

    fun editContact() {
        displayMessage("Enter contact ID to edit: ")
        val id = scanner.nextInt()
        displayMessage("Enter new name: ")
        val newName = scanner.next()
        displayMessage("Enter new phone: ")
        val newPhone = scanner.next()
        controller.editContact(id, newName, newPhone)
    }

    fun deleteContact() {
        displayMessage("Enter contact ID to delete: ")
        val id = scanner.nextInt()
        controller.deleteContact(id)
    }

    fun viewContacts() {
        val contacts = controller.viewContacts()
        displayContacts(contacts)
    }

    private fun displayContacts(contacts: List<Contact>) {
        if (contacts.isEmpty()) {
            displayMessage("No contacts available.")
        } else {
            contacts.forEach { displayMessage("ID: ${it.id}, Name: ${it.name}, Phone: ${it.phone}") }
        }
    }

    fun displayMessage(message: String) {
        println(message)
    }
}
