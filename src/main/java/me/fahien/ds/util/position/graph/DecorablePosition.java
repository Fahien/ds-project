package me.fahien.ds.util.position.graph;

import me.fahien.ds.map.Map;
import me.fahien.ds.util.position.Position;

/** Decorable position
 * @author Fahien */
public interface DecorablePosition<E> extends Position<E>, Map<Object, Object> {}