# ScalaMowItNow

La société MowItNow a décidé de développer une tondeuse à gazon automatique, destinée aux surfaces rectangulaires.
La tondeuse peut être programmée pour parcourir l'intégralité de la surface.
La position de la tondeuse est représentée par une combinaison de coordonnées (x,y) et d'une lettre indiquant l'orientation selon la notation cardinale anglaise (N,E,W,S).
La pelouse est divisée en grille pour simplifier la navigation.
Par exemple, la position de la tondeuse peut être « 0, 0, N », ce qui signifie qu'elle se situe dans le coin inférieur gauche de la pelouse, et orientée vers le Nord.
Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres. Les lettres possibles sont "D", "G" et "A".
« D » et « G » font pivoter la tondeuse de 90° à droite ou à gauche respectivement, sans la déplacer.
« A » signifie que l'on avance la tondeuse d'une case dans la direction à laquelle elle fait face, et sans modifier son orientation.
Si la position après mouvement est en dehors de la pelouse, la tondeuse ne bouge pas, conserve son orientation et traite la commande suivante.

Pour programmer la tondeuse, on lui fournit un fichier d'entrée construit comme suit :
- La première ligne correspond aux coordonnées du coin supérieur droit de la pelouse,celles du coin inférieur gauche sont supposées être (0,0).
- La suite du fichier permet de piloter toutes les tondeuses qui ont été déployées.
Chaque tondeuse a deux lignes la concernant :
- la première ligne donne la position initiale de la tondeuse, ainsi que son
orientation. La position et l'orientation sont fournies sous la forme de 2 chiffres
et une lettre, séparés par un espace.
- la seconde ligne est une série d'instructions ordonnant à la tondeuse d'explorer
la pelouse. Les instructions sont une suite de caractères sans espaces.

Pour exécuter l'application MowItNow, veillez-vous de télécharger le fichier "ApplicationMowItNow.scala" et le fichier "Input.txt" ou bien créer votre fichier d'entréer suite aux reglementations ci-dessus.
Exécuter le fichier "ApplicationMowItNow.scala".
Une fois le programme demande le répertoire du fichier d'entrée, copiez le chemin d'accès du fichier et taper "Entrer"
Les erreurs fréquentes avec le fichier d'entrée:
- fichier introuvalable: le programme n'arrive pas à trouver le fichier d'entrée à cause du faux chemin d'accès.
- le format d'entrée n'est pas valable: soit les dimensions de la pelouse ou les coordonnées de la tondeuse ne sont pas les entiers, soit il n'y a pas d'espace entre les coordonnées de la tondeues, soit il manque de la coordonnée de la tondeuse.
- L'orientation initiale n'est pas valable: l'orientation de la tondeuse n'est pas l'une des lettres "N", "E", "S" et "W".
- L'espace entre dimensions est obligatoire: il n'y a pas d'espace entre les dimensions de la pelouse.
- Si l'opération n'est pas l'une des lettres "D", "G" et "A", le programme va immprimer l'affiche, dépasser l'opération et exécuter l'opération suivante sans quiter le programme.
