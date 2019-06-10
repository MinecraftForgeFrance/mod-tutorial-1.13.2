package dev.mff.modtutorial.capability;

/**
 * Interface permettant de gérer la fatigue. La fatigue est représentée par un entier allant de 0 à 10 000, avec 10 000
 * correspondant au maximum de fatigue.
 */
public interface IExhaustable
{

    /**
     * Renvoie un nombre entre 0 et 10 000
     * @return la fatigue
     */
    int getExhaustion();

    /**
     * Permet de définir la fatigue, celle-ci est un nombre entre 0 et 10 000 (bornes incluses).
     * Dans le cas d'une valeur passée supérieure à 10 000, celle-ci est est plafonnée automatiquement.
     * Dans le cas d'une valeur négative passée, celle-ci est compté comme nulle.
     * @param value La nouvelle valeur de la fatigue
     */
    void setExhaustion(int value);

    /**
     * Réduit la fatigue.
     * @param value La quantité de fatigue à enlever
     */
    default void reduceExhaustion(int value) {
        this.setExhaustion(this.getExhaustion() - value);
    }

    /**
     * Augmente la fatigue.
     * @param value La quantité de fatigue à ajouter
     */
    default void increaseExhaustion(int value) {
        this.setExhaustion(this.getExhaustion() + value);
    }

}
