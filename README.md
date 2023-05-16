[中文](https://github.com/Luuxcyz/Java_FileEncryptor/blob/main/README.zh_CN.md)
# FileEncryptor

FileEncryptor is a simple Java program that can encrypt and decrypt files using the AES encryption algorithm. The program provides a graphical user interface (GUI) that allows the user to select one or more files to encrypt/decrypt, and displays the progress of the operation using a progress bar.

## How to use

1. Clone the repo to your local machine.
2. Open the project in your Java IDE.
3. Run the program and the GUI will be displayed.
4. Click the "Encrypt File" button to encrypt a file, or the "Decrypt File" button to decrypt a file.
5. Select one or more files to encrypt/decrypt using the file chooser dialog.
6. The progress of the operation will be displayed using a progress bar.
7. When the operation is complete, a message dialog will be displayed indicating whether the operation was successful or not.

## Dependencies

- Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files

## Note

- The program uses a fixed password ("your_password_here") for encryption and decryption. You should change this password to a secure one before using the program.
- The encrypted files will have the ".enc" extension added to their names.
- The decrypted files will have the ".enc" extension removed from their names.
- The program is provided as-is, without any warranty or guarantee of any kind. Use it at your own risk.
