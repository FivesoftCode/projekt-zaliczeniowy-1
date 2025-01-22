# Projekt: Aplikacja demonstracyjna bezpieczeństwa

## Opis projektu

Projekt demonstruje:

1. **Szyfrowanie komunikacji** - wykorzystanie algorytmu **AES** do zabezpieczania przesyłanych danych.
2. **Podatność na atak** - demonstracja **SQL Injection**.
3. **Zabezpieczenie przed atakiem** - zastosowanie **Prepared Statements**.

## Wykorzystane algorytmy i protokoły

### **1. Algorytm AES (Advanced Encryption Standard)**

- Algorytm symetrycznego szyfrowania.
- W projekcie wykorzystujemy **AES-256** w trybie **CBC** (Cipher Block Chaining).
- Do generowania klucza używamy **PBKDF2WithHmacSHA256**.

### **2. SQL Injection**

- Atak polegający na wstrzyknięciu kodu SQL do zapytań.
- Umożliwia obejście mechanizmów uwierzytelniania lub manipulację danymi.
- Przykład podatnego zapytania:
  ```sql
  SELECT * FROM users WHERE username = 'admin' AND password = 'password';
  ```
  Możliwe ominięcie logowania poprzez wpisanie `' OR '1'='1` jako login.

### **3. Zabezpieczenie przed SQL Injection**

- Użycie **Prepared Statements**:
  ```kotlin
  val query = "SELECT * FROM users WHERE username = ? AND password = ?"
  val preparedStatement = conn.prepareStatement(query)
  preparedStatement.setString(1, username)
  preparedStatement.setString(2, password)
  ```
- Zapobiega wstrzykiwaniu nieautoryzowanego kodu SQL.

## Uruchomienie

1. **Szyfrowanie komunikacji**:
   - Uruchom `AESUtil.kt`.
2. **Demonstracja ataku**:
   - Uruchom `loginUnsafe.kt` i spróbuj SQL Injection.
3. **Zabezpieczona wersja**:
   - Uruchom `loginSafe.kt`.

