# Cake Shop Business Management System

A Java-based client-server desktop application for managing the operations of a cake shop, including orders, customers, products, employees, and shifts.

> **Note:** This project was developed as a university assignment at the Faculty of Organizational Sciences, University of Belgrade, Serbia. As a result, class names, method names, and variable names throughout the codebase are written in Serbian.

---

## Architecture

The system follows a **3-tier client-server architecture** split across three modules:

```
0_Seminarski_Klijent/     → Client (GUI, forms, user interaction)
0_Seminarski_Server/      → Server (business logic, socket listener, DB access)
0_Seminarski_Zajednicki/  → Shared (domain model, communication protocol)
```

Client and server communicate over **TCP sockets** using Java object serialization. Each client request is wrapped in a `Zahtev` (Request) object containing an operation type and parameters; the server responds with an `Odgovor` (Response) object.

The server uses a **Generic Repository pattern** (`DbRepositoryGeneric`) where domain objects define their own SQL mappings via the `ApstraktniDomenskiObjekat` interface — allowing a single generic implementation to handle all CRUD operations.

---

## Features

- **Authentication** — login system for cake shop employees
- **Cake (product) management** — add, edit, delete, view products
- **Customer management** — full CRUD with city/location data
- **Order (invoice) management** — create and manage orders with line items, search by criteria
- **Employee management** — view and edit employee profiles
- **Shift management** — define and assign work shifts
- **Multi-client support** — server handles multiple simultaneous clients via threads

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java (JDK 8+) |
| IDE | NetBeans |
| GUI | Java Swing |
| Database | MySQL 8.0 |
| DB Tool | SQLyog |
| Communication | TCP Sockets + Java Serialization |

---

## Database Setup

1. Open MySQL and run the provided dump:

```bash
mysql -u root -p < poslasticarnica.sql
```

Or import `poslasticarnica.sql` via MySQL Workbench / SQLyog.

2. The script creates the `poslasticarnica` database with all tables and sample data.

**Tables:** `kolac` (products), `kupac` (customers), `mesto` (cities), `poslasticar` (employees), `smena` (shifts), `poslasticarsmena` (employee-shift), `racun` (invoices), `stavkaracuna` (invoice line items)

---

## Running the Application

### 1. Configure the Server

Edit `0_Seminarski_Server/config/config.properties` with your database credentials:

```properties
host=localhost
port=3306
database=poslasticarnica
username=root
password=your_password
```

### 2. Start the Server

Open `0_Seminarski_Server` in NetBeans and run the project. The server listens on port **9000** by default.

### 3. Start the Client

Open `0_Seminarski_Klijent` in NetBeans and run the project. Connect to `localhost:9000`.

**Sample login credentials:**
| Username | Password |
|---|---|
| marko85 | pass123 |
| ivana90 | tajna456 |
| petar82 | sifra789 |

---

## Project Structure

```
0_Seminarski_Server/src/
├── server/              # ServerSocket, thread management
├── niti/                # Client request handler thread
├── repository/
│   ├── controller/      # Singleton Controller (facade)
│   └── db/impl/         # Generic DB repository (CRUD)
├── operacija/           # System operations (SO pattern)
│   ├── kolaci/          # Product operations
│   ├── kupci/           # Customer operations
│   ├── racun/           # Invoice operations
│   ├── smene/           # Shift operations
│   └── poslasticar/     # Employee operations
└── konfiguracija/       # DB config loader

0_Seminarski_Zajednicki/src/
├── model/               # Domain classes (Kolac, Kupac, Racun, ...)
├── domen/               # Abstract domain object interface
└── komunikacija/        # Zahtev, Odgovor, Operacija (protocol)

0_Seminarski_Klijent/src/
├── forme/               # Swing GUI forms
├── forme/model/         # JTable models
└── cordinator/          # Client-side coordinator
```
