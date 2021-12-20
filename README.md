# HS Assessment Demo

## NOTES

1) Admin creds: `username: admin` `password: admin`
I also did some unit testing as well as one "integration" test which is a "smoke test" in testing the controller.
So here in the README and in testing and setup I did a incorrect practice in submitting to version control the built in admin credentials which are also version controlled.
I would have set up those respective credentials differently such as environment variables but since this is a demo, it should be fine.
2) On the instructions you requested JWT have the scope of "READ" access and Basic Auth admin have the scope of "READ/WRITE" access.
Instead of doing that generally in the `WebSecurityConfigurerAdapter` in the `configure` method, I did it specifically using the `@PreAuthroize` Spring Security annotation
on the rest controller methods.
3) Last note, I didn't use some type of "unique" identifier for the `Person` object such as social security number, ergo I allow for "duplicate" people or, for example,
another person with the same name.
4) On the JWT token for a "public" user, I set that up to have an admin hit the endpoint `/v1/get-public-user-token` to get a public JWT token as the built in `admin` with Basic Auth

## EXAMPLES

I know I included Swagger documentation but here is a JSON example that I used for the requestBody for the `post-person` api:

```{
    "firstName": "Darren",
    "lastName": "Skywalker",
    "personalInfo": {
        "email": "darrenskywalker@hotmail.com",
        "mobile": "801-123-1234"
    },
    "addresses": [
        {
            "street": "1000 Street",
            "city": "SLC",
            "state": "Utah",
            "zipcode": "84001"
        },
        {
            "street": "2000 Street",
            "city": "SLC",
            "state": "Utah",
            "zipcode": "84001"
        }
    ],
    "clubs": [{"name": "Costco"}, {"name": "Sam's Club"}]
}