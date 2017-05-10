package me.zero.client.wrapper;

import net.minecraft.util.EnumHandSide;

/**
 * Gives access to first person item
 * transformation methods.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 4/8/2017 12:00PM
 */
public interface IItemRenderer {

    /**
     * Calls ItemRenderer#transformSideFirstPerson(EnumHandSide, float)
     *
     * @since 1.0
     *
     * @param side The side being transformed for
     * @param rechargeProgress The item recharge progress
     */
    void setupSideFirstPerson(EnumHandSide side, float rechargeProgress);

    /**
     * Calls ItemRenderer#transformFirstPerson(EnumHandSide, float)
     *
     * @since 1.0
     *
     * @param side The side being transformed for
     * @param swingProgress The item swing progress
     */
    void setupFirstPerson(EnumHandSide side, float swingProgress);
}
