# Scheduled Fund Transfer Service

## Setup
1. Clone the repository.
2. Run `mvn spring-boot:run` to start the application.
3. The application will be available at `http://localhost:3419`.

## API Endpoints
- **Schedule a Transfer**: `POST /api/transfers/schedule`
    - Request Body:
      ```json
      {
          "senderAccountId": "123",
          "recipientAccountId": "456",
          "transferAmount": 100.0,
          "transferDate": "2025-01-30"
      }
      ```
    - Response:
      ```json
      {
          "transferId": 1,
          "senderAccountId": "123",
          "recipientAccountId": "456",
          "transferAmount": 100.0,
          "transferDate": "2025-01-30"
      }
      ```

- **Fetch Transfers**: `GET /api/transfers/{senderAccountId}`
    - Response:
      ```json
      [
          {
              "transferId": 1,
              "senderAccountId": "123",
              "recipientAccountId": "456",
              "transferAmount": 100.0,
              "transferDate": "2025-01-30"
          }
      ]
      ```