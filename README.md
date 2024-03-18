## Le Bioréacteur
### Jumeau Numérique de bioréacteur en version distribuée

À mesure que l'expérience est simulée à travers les données fournies, un client reçoit en temps réel l'actualisation de toutes les variables et peut encore choisir un instant quelconque pour retrouver l'état du système à ce stade.

### Architecture
[Simplified Architecture](https://enstabretagne-my.sharepoint.com/:i:/g/personal/victor_queiroz_ensta-bretagne_org/EYz3aq8TPSJMu6IilJv9rIMBZR3Pk8VQ2UkMeWuMX0f8nQ?e=Vdpe5u)

Pour comprendre comment le programme fonctionne, une description de ces étapes dans l'ordre est intéressante.

1. Le bioréacteur est initialisé et fait appel au Datamanager. Ce dernier lit tout le fichier et stocke le contenu en _hash maps_ (dictionnaires) selon les paramètres définis, ce qui permet au client de retrouver rapidement sa demande (indépendamment de la quantité de données). Pour cette implémentation, on observe l'obéissance au principe de SOLID en raison de la dépendance de cette classe à une liste de paramètres. Cela évite le couplage entre la classe et les sous-classes de paramètres, car les deux dépendent des abstractions : la liste d'objets de la classe paramètre. Comme toutes les filles de cette classe substituent cette super-classe, le principe de Liskov est également rempli. Le code est un peu répétitif, cependant, nous avons été orientés à penser que chaque variable devrait être générique et pourrait être représentée différemment, auquel cas l'implémentation choisie serait nécessaire puisqu'elle permet de ne changer que les portions liées à la variable d'intérêt sans affecter les autres.
2. Ensuite, le serveur TCP est lancé. Celui-ci suit une structure similaire à celle présentée lors du cours, car il s'agit d'un serveur TCP simple en _busy wait_ attendant le lancement du client. Plus notable est la classe ServeurSpecifique, responsable de transmettre des variables, initialement au format double, du serveur au client sous forme de String. La boucle implémentée découple ce morceau de code de la quantité et de la définition des variables.
3. PropertyChangeSupport a sa première utilisation ; à partir de lui, le patron observateur et observé est implémenté puisqu'il déclenche un événement chaque fois que les paramètres sont actualisés, ce que permet aux autres classes qu'elles ont suffert un changement. Cela est fondamental à la simulation du temps qui s'écoule dans le bioréacteur entre chaque mesure.
4. Aussi important est la initialisation d'un element runnable et l'usage des promesses pour permettre le code de répoduire justement l'écoulement du temps precedemment mentionnée. C'est une implémentation simple mais complète du patron state, vu que elle crée une modèlè comportemental qui permet à des objets de modifier son comportement lorsque un état interne change. Dans ce cas les paramètres dans le bioreacteur qui emmennent au changement de BioreacteurGUI, méthode PropertyChange.
5. Pour les interfaces graphiques du client et du bioréacteur le patron de composite est employé, cette choix est basé dans le fait que la structure en arbre pour les elements qui compesent les interfaces visuelles est naturelle. On peu comprendre le GUI comme un carré qu'on subdivise en plusieur carrées successivement avec les Strings despcriptives et/ou champs à remplir dans la fin. Même si chaque portion est lègerement different, des abstractions permettent la conception en arbre.
6. À la fin, pour le client, les Strings sont *parsed* pour retrouver les valeurs proches des valeurs doubles envoyés. Le client pourrait demander les données de différents façons, dont deux sont implémentés. Une en continu et l'autre sous demande directe d'un moment spécifique. Le patron stratégie aurait été plus utile si le programme changeait selon des contextes, mais les deux façons ont été considérées importantes et le contexte enlevé. Néanmoins, l'architecture choisie permet qu'en ajoutant une interface pour le contexte et les méthodes/stratégies la pièce soit enlargi pour accepter plusieurs stratégies.

[Complete Architecture](https://enstabretagne-my.sharepoint.com/:i:/g/personal/victor_queiroz_ensta-bretagne_org/EXojc2LkLolFiJ3FAP_43tQBTGpfWETSFKZh-Nl6q7f1oQ?e=yTXuhr)

#### Representation en Diagramme de Séquence

Pour ilustrer ce que vient d'être décrit un diagramme a été conçu Il est simplifié tant en termes de la communication, qu'est réalisé en continu, comme en enlevant les *handshakes* qui la lancent.

[Diagramme Sequentiel Simple](https://enstabretagne-my.sharepoint.com/:i:/g/personal/victor_queiroz_ensta-bretagne_org/EXa6kITP1N1Gp6sh2NhQlk8BEeXAKIIp_FYSuyhT0qPS6A?e=gCgxRv)

#### Points à améliorer

Par dessin, le programme présente des problèmes en quelques cas:
1. Demande d'un paramètre pas encore simulé par le Client. C'est impossible d'un point de vue logique donner au client les informations de ce qui n'a pas encore été simulé, cependant une message d'erreur serait préférable a la faillite du programme.
2. Lancement du client avant le serveur. En ce cas on voit des paramètres impossibles.
3. La fin de l'expériment avant la fin du temps totale de simulation.

D'un autre côté, le programme est plutôt simple et plusieurs patrons de conception sont utilisés, l'amélioration est toujours possible mais la structure a été jugée satisfaisante.
