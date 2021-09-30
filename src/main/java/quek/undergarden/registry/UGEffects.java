package quek.undergarden.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.potion.*;

public class UGEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Undergarden.MODID);

    public static final RegistryObject<MobEffect> GOOEY = EFFECTS.register("gooey", GooeyEffect::new);
    public static final RegistryObject<MobEffect> BRITTLENESS = EFFECTS.register("brittleness", BrittlenessEffect::new);
    public static final RegistryObject<MobEffect> FEATHERWEIGHT = EFFECTS.register("featherweight", FeatherweightEffect::new);
    public static final RegistryObject<MobEffect> VIRULENT_RESISTANCE = EFFECTS.register("virulent_resistance", VirulentResistanceEffect::new);
}