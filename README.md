# TaskMaster

TaskMaster is a bespoke task management application designed to streamline workflows, improve collaboration, and enhance productivity within ABC Corp. It provides a user-friendly, feature-rich solution for task creation, assignment, tracking, and prioritization, promoting effective communication among team members.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Contributing](#contributing)
- [License](#license)

## Features

- **User Authentication and Access Control**
    - Secure user registration, login, and account management.
    - Role-based access control to ensure appropriate permissions.

- **Task Management**
    - Task creation with details such as title, description, due date, priority, and assignees.
    - Task assignment to individuals or teams.
    - Task tracking with status updates, comments, and attachments.
    - Task prioritization with different priority levels.
    - Deadline management with notifications for upcoming and overdue tasks.
    - Task filters and search functionality.

- **Collaboration and Communication**
    - In-task comments and discussions.
    - Real-time notifications for task assignments, comments, and status changes.

- **Integration and Compatibility**
    - Email notifications and task interaction via email clients.
    - Calendar integration to sync tasks with due dates.

- **Mobile Compatibility**
    - Responsive design for seamless access on desktop and mobile devices.

## Tech Stack

- **Frontend**: React.js
- **Backend**: Spring Boot
- **Database**: PostgreSQL
- **Authentication**: JSON Web Tokens (JWT)
- **Deployment**: Docker

## Installation

### Prerequisites

- Node.js
- Java (JDK)
- Docker
- PostgreSQL

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/taskmaster.git
   cd taskmaster
   ```

2. **Frontend Setup**
   ```bash
   cd frontend
   npm install
   npm start
   ```

3. **Backend Setup**
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```

4. **Database Setup**
    - Create a new PostgreSQL database.
    - Update the database configuration in the backend application properties.

5. **Docker Setup**
   ```bash
   docker-compose up --build
   ```

## Usage

1. **Register a new user**
2. **Log in with your credentials**
3. **Create and manage tasks**
4. **Assign tasks to team members**
5. **Track task progress and updates**
6. **Collaborate through comments and discussions**
7. **Receive real-time notifications**

## API Endpoints

### Authentication

- **POST /api/auth/register** - Register a new user
- **POST /api/auth/login** - Log in an existing user

### Tasks

- **GET /api/tasks** - Get all tasks
- **POST /api/tasks** - Create a new task
- **GET /api/tasks/:id** - Get a specific task
- **PUT /api/tasks/:id** - Update a task
- **DELETE /api/tasks/:id** - Delete a task

### Comments

- **POST /api/tasks/:taskId/comments** - Add a comment to a task
- **GET /api/tasks/:taskId/comments** - Get comments for a task

### Notifications

- **GET /api/notifications** - Get all notifications

## Database Schema

Refer to the `schema.sql` file in the repository for the detailed database schema.

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository
2. Create a new feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Create a new Pull Request

## License

TaskMaster is licensed under the MIT License. See the LICENSE file for more details.