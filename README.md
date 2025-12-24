## StaffMate

**StaffMate** is a **Kotlin Multiplatform (KMP)** staff management and attendance tracking
application designed for **restaurants, cafés, and multi-branch businesses**, with strong support
for **NFC workflows**, **offline-first operation**, and **accurate payroll calculations** across
Android and iOS.

---

## What StaffMate Does

- **NFC-based clock-in / clock-out**
    - Works on both **Android & iOS**
    - Secure staff identification via NFC cards/tags

- **NFC staff data management**
    - Read and write staff information using NFC
    - Unified behavior across platforms

- **Accurate working hours tracking**
    - Supports business days, custom shifts, and **overnight shifts**
    - Correct hour calculation across midnight boundaries (e.g. after 12:00 AM)

- **Borrowings & deductions**
    - Salary advances
    - Lateness penalties
    - Hygiene or policy violations

- **Extras & allowances**
    - Overtime
    - Transportation
    - Bonuses and incentives

- **Automated payroll generation**
    - Accurate payroll calculation
    - Export to **PDF** and **Excel**

- **Role-based access control**
    - Managers vs staff permissions

- **Offline-first architecture**
    - Fully functional without internet
    - Sync when connectivity is available

- **Multi-branch & chain support**
    - Designed for single restaurants up to full restaurant chains

- **Historical records**
    - Access staff data by **year, month, or day**
    - Long-term history-based reporting

---

## Technologies Used

- **Kotlin Multiplatform (KMP)**
- **Compose Multiplatform** for shared UI and business logic

### Concurrency & Reactivity

- **Kotlinx Coroutines**
- **Flow-based reactive state**

### Date & Time

- **Kotlinx DateTime**
- Accurate cross-platform time calculations (including overnight shifts)

### Data & Persistence

- **Room (KMP)** for local database
- **Room FTS4** for efficient full-text search
- **KMP DataStore** for lightweight preferences

### Serialization & Parsing

- **Kotlinx Serialization**

### State Management

- **KMP ViewModel**
- Shared state across Android & iOS

### NFC

- Cross-platform **NFC read/write**
- Platform-specific implementations behind shared interfaces

### Payroll & Documents

- **Canvas drawing**
- **PDF generation** for payroll reports (Android & iOS)

### Networking & Sync

- **Firebase**
    - Firebase Authentication
    - Firebase Firestore
    - Firebase Storage

### Dependency Injection

- **Koin Multiplatform**
- **Koin Compose Multiplatform**


