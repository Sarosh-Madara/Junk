# Weather API

A simple Spring Boot application that provides weather information using the free [Open-Meteo API](https://open-meteo.com/).

## Features

- Get current weather data by geographic coordinates (latitude/longitude)
- Returns temperature, wind speed, and weather description
- Uses the free Open-Meteo API (no API key required)

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Running the Application

### Build and Run

```bash
# Build the project
mvn clean compile

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

### Run Tests

```bash
mvn test
```

## API Endpoints

### Get Weather

```
GET /api/weather?latitude={lat}&longitude={lon}
```

**Parameters:**
- `latitude` (required): Latitude coordinate (e.g., 52.52 for Berlin)
- `longitude` (required): Longitude coordinate (e.g., 13.41 for Berlin)

**Example Request:**
```bash
curl "http://localhost:8080/api/weather?latitude=52.52&longitude=13.41"
```

**Example Response:**
```json
{
  "latitude": 52.52,
  "longitude": 13.41,
  "temperature": 15.5,
  "windSpeed": 10.2,
  "temperatureUnit": "°C",
  "windSpeedUnit": "km/h",
  "description": "Clear sky"
}
```

### Sample Coordinates

| City          | Latitude | Longitude |
|---------------|----------|-----------|
| Berlin        | 52.52    | 13.41     |
| New York      | 40.71    | -74.01    |
| London        | 51.51    | -0.13     |
| Tokyo         | 35.68    | 139.69    |
| Sydney        | -33.87   | 151.21    |

## Technology Stack

- Spring Boot 3.2.0
- Java 17
- Maven
- Open-Meteo API (free weather data)

## License

Apache License 2.0
