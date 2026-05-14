# ATM Demo (Java)

A simple Object-Oriented ATM demo with input validation, PIN login, and transaction history.

Features
- `BankAccount` class: encapsulated account logic (deposit, withdraw, balance, history)
- `ATM` class: text UI (menu loop, PIN login, input validation using `Scanner`)
- Validations: non-negative amounts, sufficient funds, non-crashing on non-numeric input
- Default demo PIN: `1234`

Build & Run

Option A — run from project root (recommended):
```powershell
cd D:\Decodelabs
javac ATM\*.java
java ATM.ATM
```

Option B — run from inside `ATM` using parent classpath:
```powershell
cd D:\Decodelabs\ATM
java -cp .. ATM.ATM
```

Option C — remove `package ATM;` from sources if you prefer running with `java ATM` from inside the `ATM` folder.

Usage
1. Enter PIN (default `1234`).
2. Use the menu to deposit, withdraw, check balance, or view transaction history.
3. Enter numeric amounts only; invalid inputs are handled gracefully.

Notes
- Files: `BankAccount.java`, `ATM.java` (located in this folder).
- Keep `package ATM;` present to use the fully-qualified run command (`ATM.ATM`).

If you want, I can run the interactive demo now or change the default PIN.```