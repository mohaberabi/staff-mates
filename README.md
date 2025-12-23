## StaffMate

Kotlin Multiplatform Staff management & Attendance Tracking With NFC App

## What it does

NFC clock-in/clock-out for employees (works on both Android & iOS).
NFC reading and writing staff info  (works on both Android & iOS).
Manaing the working hours and business days and support overnight shifts
(eg : after 12 :00 am to accurate total worked hours calculations )
Borrowings & deductions management (salary advances, lateness, hygiene issues, etc.).
Extras/allowances tracking (overtime, transportation, bonuses).
Automated payroll generation with PDF/Excel export.
Role-based access for managers and staff.
Offline-first sync so it works even without internet.
Multi-branch and even restaurant chains support for larger operations.
History based solution for past years , moths ,days

## Technologies Used

Framework: Kotlin Multiplatform + Compose Multiplatform for shared UI and logic.
Reactive & Async Programming : Kotlinx Coroutines
Date & Time Manipulations : KotlinX DateTime
Data Parsing & Serialization : KotlinX Serialization
Efiecient Full Text Search Using Kmp Room FTS4
Storage: KMP Room for local database, KMP DataStore for lightweight preferences.
State management: KMP ViewModel for shared state across platforms.
NFC integration: Reading & writing on both Android and iOS.
PDF generation: Canvas drawing & PDF export for payroll reports (works on both platforms).
Networking & sync: We Decided to go production for the first version using Firebase , i build my own
firebase auth , firebasae firestore, firebase storage shared business logic using each platform (
ios - android ) specific implementation) but i decided to make use of our library in the next
versions and i used a released open source library for kmp firebase as for now as it was already
tested , but my solution iam still testing it for now
Dependency Injection:  Koin Multiplatform + Koin Compose Multiplatform  
UI/UX: Simple Minimal Designed for simplicity with multi language support (Arabic & English) +
Material3 Compose Multiplatform Navigation , Coil Compose multiplatform Image Networking Library
Loader 