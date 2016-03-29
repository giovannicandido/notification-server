# Sending Email from Other Applications

Generate a token for the application in the application Admin Page (the default page)

Send a **POST** to the application url: **/api/notification/email** with the contents:

| Request Param | Description                                                    | Constraints           |
|---------------|----------------------------------------------------------------|-----------------------|
| token         | Token for security                                             | Required              |
| email         | Destination Email, multiples emails are separated by comma (;) | Required. Valid email |
| subject       | Subject of the message                                         | Required              |
| html          | If the email is Html content                                   | Option                |

