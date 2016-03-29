# Sending Email from Other Applications

Generate a token for the application in the application Admin Page (the default page)

Send a **POST** to the application url: **/api/notification/email** with the contents:

| Request Param | Description                                                    | Constraints           |
|---------------|----------------------------------------------------------------|-----------------------|
| token         | Token for security                                             | Required              |
| email         | Destination Email, multiples emails are separated by comma (;) | Required. Valid email |
| subject       | Subject of the message                                         | Required              |
| message       | The content of the message                                     | Required              |
| html          | If the email is Html content                                   | Option                |
| from          | From address                                                   | Option. Valid email   |

Response

The application will respond with a 200 OK if the email is sended. The operation is assincronous, so, is **NOT**
guaranteed the email will be sended, if something goes wrong open the application log files.
An JSON with the content **{"message":"Email enviado","data":null,"success":true}** is returned

If the request has some problem it will return a **400** or a **500**

Example of RestClient JAX-RS 2.0

    val restClient = ClientBuilder.newClient()
    val target = restClient.target(serviceUrl)
    val form = new Form()
    form.param("token", serviceToken)
    form.param("from", from)
    form.param("to", email)
    form.param("subject", subject)
    form.param("html","true")
    form.param("message",htmlContent)
    val response = target.request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE), classOf[String])

