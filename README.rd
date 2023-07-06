***********************************************************************************
                            -Övergripande kodstruktur-
***********************************************************************************
*--------------------------------------Admin--------------------------------------*
http://localhost:8080/admin
Name: admin
Password: admin

-Product management
Lägg till / ändra produkter

-Order management
Ändra status för lagd order

*------------------------------------Customer------------------------------------*
http://localhost:8080/login

Skapa medlemsskap och logga in för kunder

Vid inloggning kopplas kund vidare till http://localhost:8080/store

Här kan kund lägga till produkter i sin kundkorg samt söka (specifikt eller efter kategorier)

Klicka vidare till "See cart" där uppdaterar kund sina föremål och genomföra köp via "Make purchase"



************************************************************************************
                                  -Teknikval-
************************************************************************************

|- Thymeleaf / Maven / Spring Boot / Validator / jUnit / Mockito / Mysql / Docker -|



************************************************************************************
                                  -Designbeslut-
************************************************************************************

Design för funktionalitet.
Inloggningssida för Admin / Kund är separata (Admin ska vara "Intranätet" för företaget)



************************************************************************************
                                   -Arkitektur-
************************************************************************************
Jag har valt att ha en separat uppsättning av Model, Dao(Repository), Service och Controller för varje "Entitet"
Den process som tillhör en specifik entitet hittas under motsavarande class (Model, Dao, Service och Controller).

Undantaget är OrderService där flera entiteter sammankopplas. För att förbättra arkitekturen och få en mer överskådlig
kod hade en övergripande service, "Webshop Service", kunnat implementeras.

Till skillnad från nuläget där OrderService både innehåller kod tillhörande Order och kopplingen mellan övriga
entiteter.

