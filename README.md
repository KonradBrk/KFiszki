# KFiszki
REST API based on the Java Spring Boot and MongoDB technologies.

# Description of endpoints

### All users

POST /api/register

POST /api/login

### As admin

GET /api/users

GET /api/users/{name}

DELETE /api/users/{id}

PATCH /api/users/{id}

POST /api/newrole


### As user
(packages of flashcards)

POST /api/{username}/{flashcardPackageId}/newflashcard

GET /api/{username}/{flashcardPackageId}/allflashcards

GET /api/{username}/{flashcardPackageId}/{flashcardId}

DELETE /api/{username}/{flashcardPackageId}/{flashcardId}

PATCH /api/{username}/{flashcardPackageId}/{flashcardId}

### (flashcards directly)
POST /api/{username}/newflashcardpackage

GET /api/{username}/allflashcardpackages

GET /api/{username}/{flashcardPackageId}

DELETE /api/{username}/{flashcardPackageId}

PATCH /api/{username}/{flashcardPackageId}
