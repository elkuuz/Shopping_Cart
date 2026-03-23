# Shopping Cart (Maven)

This project includes:
- Maven build and tests (JUnit 5 + JaCoCo)
- Jenkins Declarative Pipeline (`Jenkinsfile`)
- Docker containerization (`Dockerfile`)

## Requirements

- JDK 21
- Maven 3.9+
- Docker
- Jenkins with Pipeline + Git + Credentials plugins

## Run locally

```bash
mvn -q clean test
mvn -q verify
mvn -q exec:java -Dexec.mainClass="com.shoppingcart.Main"
```

## Docker

Build image:

```bash
docker build -t elkuuz/shopping-cart:local .
```

Run image (interactive app):

```bash
docker run --rm -it elkuuz/shopping-cart:local
```

## Jenkins Pipeline Setup

1. Push `Jenkinsfile` to your GitHub repo.
2. In Jenkins, create a **Pipeline** or **Multibranch Pipeline** from SCM.
3. Set SCM to your GitHub repository.
4. In **Manage Jenkins -> Global Tool Configuration**, ensure tool names match the Jenkinsfile:
   - JDK: `JDK21`
   - Maven: `Maven`
5. Add Docker Hub credentials in **Manage Jenkins -> Credentials**:
   - Kind: `Username with password`
   - ID: `dockerhub-creds`
6. Ensure your Jenkins agent has Docker CLI access.

The pipeline stages are:
- Checkout
- Test (`mvn -B clean test`)
- Verify (`mvn -B verify`)
- Docker Build
- Docker Push (only on `main`/`master`)

## Docker Hub Deployment

The Jenkinsfile pushes these tags:
- `elkuuz/shopping-cart:${BUILD_NUMBER}`
- `elkuuz/shopping-cart:latest`

If your Docker Hub repo is different, update `DOCKERHUB_REPO` in `Jenkinsfile`.

