# Reppd

This repository represents the RESTful API for Reppd. Reppd allows you to track daily habits and monitor progress towards competencies over time. 

Each competency consists of multiple daily disciplines, and you can then log daily activities for each discipline, such as minutes spent on that activity, exercises completed, etc.

## Features

- Create and manage competencies (e.g., Spanish Language).
- Add and manage daily disciplines for each competency (e.g., reading books, listening to a podcast, speaking practice).
- Log daily activities for each discipline (e.g., minutes spent, exercises completed).
- Retrieve data to visualize progress over time.

## Requirements

- Java 17
- Maven
- MySQL

## Installation

### 1. Clone the Repository
```shell
git clone https://github.com/jakegodsall/reppd
cd reppd
```

### 2. Build the project with Maven

```shell
mvn clean install
```

### 3. Run the application

```shell
mvn spring-boot:run
```

## Example Usage

### 1. Create a competency

**Endpoint:** `POST api/v1/competencies`

**Request Body:**

```json
{
  "title": "Spanish Language",
  "description": "Become fluent in the Spanish language"
}
```
**Response**
```json
{
  "id": "<unique_uuid>",
  "title": "Spanish Language",
  "description": "Become fluent in the Spanish language",
  "status": "ACTIVE",
  "startDate": "Current Datetime"
}
```

### 2. Add a DailyDiscipline to the Competency

**Endpoint:** `POST api/v1/competencies/1/daily-disciplines`

**Request Body:**

```json
{
  "title": "Listen to podcast X",
  "description": "Listen to podcast X for 10 minutes per day",
  "status": "ACTIVE",
  "minimumValue": 10
}
```
**Response**
```json
{
  "id": "<unique_uuid>",
  "competency_id": "<unique_uuid>",
  "title": "Listen to podcast X",
  "description": "Listen to podcast X for 10 minutes per day",
  "minimumValue": 10
}
```

### 3. Log a DailyLog for the DailyDiscipline

**Endpoint:** `POST api/v1/daily-disciplines/1/daily-logs`

**Request Body:**

```json
{
  "value": 5
}
```
**Response**
```json
{
  "id": "<unique_uuid>",
  "daily_discipline_id": "<unique_uuid>",
  "value": 5,
  "logDate": "Current Datetime"
}
```

### 4. Get all DailyLogs for a DailyDiscipline

**Endpoint:** `GET api/v1/daily-disciplines/1/daily-logs`

**Response**
```json
[
  {
    "id": "<unique_uuid>",
    "daily_discipline_id": "<unique_uuid>",
    "value": 5,
    "logDate": "Current Datetime"
  },
  {
    "id": "<unique_uuid>",
    "daily_discipline_id": "<unique_uuid>",
    "value": 10,
    "logDate": "Current Datetime"
  }
]
```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes. Ensure that your code follows the project's coding standards and includes appropriate tests.

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Create a new Pull Request
   
## License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.

