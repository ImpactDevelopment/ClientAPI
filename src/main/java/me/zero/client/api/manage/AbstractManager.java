package me.zero.client.api.manage;

/**
 * Marks a manager as being intended for storing entries
 * with an abstract superclass. If Manager#get(Class) is
 * accessed without this interface being implemented, then
 * an {@code ActionNotSupportedException} will be thrown.
 *
 * @author Brady
 * @since 5/24/2017 10:15 AM
 */
public interface AbstractManager {}
