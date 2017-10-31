# Spring Boot with Bearer Only Keycloak Authentication Adapter

A simple example on how to run Spring Boot with Keycloak Bearer Only authentication. 

Unfortunately this example does not work for some reason, the Keyccloak Tomcat Valve fails before it can even parse the Bearer token.

The evaluation goes until about `org.apache.catalina.realm.RealmBase:L853` and then it works out that there is no security principal in the context and dies.

**1\.** Configure the Keycloak Adapter in `application.yml` file

**2\.** Start Spring Boot App

```bash
mvn spring-boot:run
```

**3\.** Get a valid access token from your IdP

**4\.** Curl the secured path 

```bash
AT='[token-here]'
curl -H "Authorization: Bearer ${AT}" http://localhost:8989/v1/secured?name=Fred
```

This is the trace log that shows that it dies in the Tomcat pipe. 

```xml
21:22:19.449 [http-nio-8989-exec-1] DEBUG o.k.adapters.PreAuthActionsHandler - adminRequest http://localhost:8989/v1/secured?name=Fred
21:22:19.764 [http-nio-8989-exec-1] DEBUG o.k.a.a.ClientCredentialsProviderUtils - Using provider 'secret' for authentication of client 'mobile-api'
21:22:19.766 [http-nio-8989-exec-1] DEBUG o.k.a.a.ClientCredentialsProviderUtils - Loaded clientCredentialsProvider secret
21:22:19.766 [http-nio-8989-exec-1] DEBUG o.k.a.a.ClientCredentialsProviderUtils - Loaded clientCredentialsProvider jwt
21:22:19.767 [http-nio-8989-exec-1] DEBUG o.a.c.loader.WebappClassLoaderBase -     findResources(META-INF/services/org.keycloak.adapters.authentication.ClientCredentialsProvider)
21:22:19.767 [http-nio-8989-exec-1] DEBUG o.k.a.a.ClientCredentialsProviderUtils - Loaded clientCredentialsProvider secret
21:22:19.767 [http-nio-8989-exec-1] DEBUG o.k.a.a.ClientCredentialsProviderUtils - Loaded clientCredentialsProvider jwt
21:22:19.772 [http-nio-8989-exec-1] DEBUG o.k.adapters.KeycloakDeployment - resolveUrls
21:22:19.773 [http-nio-8989-exec-1] DEBUG o.k.a.KeycloakDeploymentBuilder - Use authServerUrl: http://localhost:8080/auth, tokenUrl: http://localhost/auth/realms/demo/protocol/openid-connect/token, relativeUrls: NEVER
21:22:19.775 [http-nio-8989-exec-1] DEBUG o.a.c.a.AuthenticatorBase - Security checking request GET /v1/secured
21:22:19.776 [http-nio-8989-exec-1] DEBUG org.apache.catalina.realm.RealmBase -   Checking constraint 'SecurityConstraint[authed]' against GET /v1/secured --> true
21:22:19.778 [http-nio-8989-exec-1] DEBUG o.a.c.a.jaspic.AuthConfigFactoryImpl - Loading persistent provider registrations from [C:\Users\fred\AppData\Local\Temp\tomcat.5760305853319586434.8989\conf\jaspic-providers.xml]
21:22:19.778 [http-nio-8989-exec-1] DEBUG o.a.c.a.AuthenticatorBase -  Calling hasUserDataPermission()
21:22:19.779 [http-nio-8989-exec-1] DEBUG org.apache.catalina.realm.RealmBase -   User data constraint has no restrictions
21:22:19.779 [http-nio-8989-exec-1] DEBUG o.a.c.a.AuthenticatorBase -  Calling accessControl()
21:22:19.779 [http-nio-8989-exec-1] DEBUG org.apache.catalina.realm.RealmBase -   Checking roles null
21:22:19.779 [http-nio-8989-exec-1] DEBUG org.apache.catalina.realm.RealmBase -   No user authenticated, cannot grant access
21:22:19.779 [http-nio-8989-exec-1] DEBUG o.a.c.a.AuthenticatorBase -  Failed accessControl() test
```