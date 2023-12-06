# OpenAPI definition v0

## Servers
- URL: http://BookAMeeting-env.eba-ps2sjy6i.ap-south-1.elasticbeanstalk.com
  - Description: Generated server URL

## Paths
### /meeting/reschedule
- Method: POST
  - Tags:
    - meeting-controller
  - Operation ID: rescheduleMeeting
  - Request Body:
    - Content:
      - application/json:
        - Schema:
          - $ref: '#/components/schemas/RescheduleRequestDto'
    - Required: true
  - Responses:
    - "200":
      - Description: OK
        - Content:
          - '*/*':
            - Schema:
              - $ref: '#/components/schemas/MeetingRescheduledResponseDto'
  - Summary: Endpoint for rescheduling a meeting.

### /meeting/recurring
- Method: POST
  - Tags:
    - meeting-controller
  - Operation ID: rebookMeeting
  - Request Body:
    - Content:
      - application/json:
        - Schema:
          - $ref: '#/components/schemas/ReebookRequestDto'
    - Required: true
  - Responses:
    - "200":
      - Description: OK
        - Content:
          - '*/*':
            - Schema:
              - Type: array
                - Items:
                  - $ref: '#/components/schemas/RebookResponseDto'
  - Summary: Endpoint for creating a recurring meeting.

### /meeting/cancel
- Method: GET
  - Tags:
    - meeting-controller
  - Operation ID: cancelMeeting
  - Parameters:
    - Name: meetingId
      - In: query
      - Required: true
      - Schema:
        - Type: integer
          - Format: int64
  - Responses:
    - "200":
      - Description: OK
        - Content:
          - '*/*':
            - Schema:
              - $ref: '#/components/schemas/MeetingResponseDTO'
  - Summary: Endpoint for canceling a meeting.

## Components
### Schemas
- RescheduleRequestDto:
  - Type: object
    - Properties:
      - meetingId:
        - Type: integer
          - Format: int64
      - newBookingDate:
        - Type: string
  - Summary: Request body for rescheduling a meeting.

- MeetingRescheduledResponseDto:
  - Type: object
    - Properties:
      - id:
        - Type: integer
          - Format: int64
      - status:
        - Type: string
      - bookedAt:
        - Type: string
          - Format: date-time
  - Summary: Response body for a rescheduled meeting.

- ReebookRequestDto:
  - Type: object
    - Properties:
      - clientId:
        - Type: integer
          - Format: int64
      - consultantId:
        - Type: integer
          - Format: int64
      - startDate:
        - Type: string
      - endDate:
        - Type: string
      - startTime:
        - Type: string
      - frequency:
        - Type: string
  - Summary: Request body for creating a recurring meeting.

- RebookResponseDto:
  - Type: object
    - Properties:
      - id:
        - Type: integer
          - Format: int64
      - date:
        - Type: string
      - time:
        - Type: string
  - Summary: Response body for a recurring meeting.

- MeetingResponseDTO:
  - Type: object
    - Properties:
      - id:
        - Type: integer
          - Format: int64
      - status:
        - Type: string
  - Summary: Response body for a meeting cancellation.
