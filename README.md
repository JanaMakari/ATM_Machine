# ATM_Machine
This program simulates a banking system where accounts are created with unique IDs and balances. It provides a simplified alternative to a database system, allowing users to access their accounts using IDs and passwords.
the ids are : 111 112 121 122 211 212 221 222.
Account Generation
Accounts are created with unique IDs and corresponding balances. The program includes a method to generate a list of unique IDs. By default, it generates 8 IDs, resulting in 8 distinct accounts, each with its own balance.
User Interaction
-Account Access: The program prompts the user to enter an ID to access their account. The available IDs are predefined within the program.
-Password Validation: After selecting an ID, the user is prompted to enter a password. The password must meet specific criteria:
.At least 8 characters long.
.Contains at least one uppercase letter.
.Contains at least one lowercase letter.
.Contains at least one digit.
-Account Options: Upon successful validation, the program provides options to the user:
-View Current Balance: Displays the current balance saved in the account.
-Make Withdrawal: Allows the user to withdraw funds from their account. The program validates the withdrawal amount and ensures it does not exceed the account balance.
-Make Deposit: Allows the user to deposit funds into their account.
-Exit Main Menu: Returns the user to re-enter their ID.
