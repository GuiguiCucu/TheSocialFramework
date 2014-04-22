package core.models.modules.contacts;

public abstract class Contact<C> {

	/**
	 * Permet de comparer deux objets et retourne true s'ils sont identiques.
	 * @param c
	 * @return bool
	 */
	public abstract boolean compareTo(Contact<C> c);
}
