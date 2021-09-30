package quek.undergarden.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;

public class UGFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Undergarden.MODID);

    public static final RegistryObject<FlowingFluid> VIRULENT_MIX_SOURCE = FLUIDS.register("virulent_mix_source", () -> new ForgeFlowingFluid.Source(UGFluids.VIRULENT_MIX_PROPERTIES));
    public static final RegistryObject<FlowingFluid> VIRULENT_MIX_FLOWING = FLUIDS.register("virulent_mix_flowing", () -> new ForgeFlowingFluid.Flowing(UGFluids.VIRULENT_MIX_PROPERTIES));

    public static final ForgeFlowingFluid.Properties VIRULENT_MIX_PROPERTIES = new ForgeFlowingFluid.Properties(VIRULENT_MIX_SOURCE, VIRULENT_MIX_FLOWING, FluidAttributes.builder(new ResourceLocation(Undergarden.MODID, "fluid/virulent_mix_still"), new ResourceLocation(Undergarden.MODID, "fluid/virulent_mix_flow"))).bucket(UGItems.VIRULENT_MIX_BUCKET).block(UGBlocks.VIRULENT_MIX);
}