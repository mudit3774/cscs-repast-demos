/*
 * Version info:
 *     $HeadURL$
 *     $LastChangedDate$
 *     $LastChangedRevision$
 *     $LastChangedBy$
 */
package stupidmodel;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.query.space.grid.GridCell;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.GridPoint;

/**
 * Dedicated class for declaring utility functions that is uninstantiable.
 * 
 * <p>
 * Final class, cannot be extended.
 * </p>
 * 
 * @author Richard O. Legendi (richard.legendi)
 * @since 2.0-beta, 2011
 * @version $Id$
 */
public final strictfp class SMUtils {

	// Unused yet
	public static <T> List<GridPoint> getGridPoints(
			final List<GridCell<T>> cellList) {
		if (null == cellList) {
			throw new IllegalArgumentException(
					"Parameter cellList cannot be null.");
		}

		final ArrayList<GridPoint> ret = new ArrayList<GridPoint>();

		for (final GridCell<T> act : cellList) {
			ret.add(act.getPoint());
		}

		return ret;
	}

	/**
	 * Returns a filtered list of {@link GridCell} instances from the specified
	 * collection which holds no objects.
	 * 
	 * @param neighborhood
	 *            an arbitrary list of <code>GridCell</code> objects; <i>cannot
	 *            be <code>null</code></i>
	 * @return a filtered list of empty <code>GridCell</code> objects, more
	 *         specifically for which <code>size()</code> returns <code>0</code>
	 *         ; if there is no empty cell in the specified list, an empty list
	 *         is returned
	 */
	public static <T> List<GridCell<T>> getFreeGridCells(
			final List<GridCell<T>> neighborhood) {
		if (null == neighborhood) {
			throw new IllegalArgumentException(
					"Parameter neighborhood cannot be null.");
		}

		final ArrayList<GridCell<T>> ret = new ArrayList<GridCell<T>>();

		for (final GridCell<T> act : neighborhood) {
			if (0 == act.size()) {
				ret.add(act);
			}
		}

		return ret;
	}

	/**
	 * Returns a random element of the specified arbitrary list.
	 * 
	 * @param list
	 *            the list to select a random element from; <i>cannot be
	 *            <code>null</code> or empty</i>
	 * @return a random element of the specified list using the default random
	 *         generator of {@link RandomHelper}
	 */
	public static <T> T randomElementOf(final List<T> list) {
		if (null == list) {
			throw new IllegalArgumentException("Parameter list cannot be null.");
		}

		if (list.isEmpty()) {
			throw new IllegalArgumentException(
					"Cannot return a random element from an empty list.");
		}

		return list.get(RandomHelper.nextIntFromTo(0, list.size() - 1));
	}

	/**
	 * Hidden constructor to ensure no instances are created.
	 */
	private SMUtils() {
		;
	}

}