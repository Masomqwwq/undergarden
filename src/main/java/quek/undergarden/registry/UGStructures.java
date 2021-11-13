package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.structure.CatacombsStructure;

import java.util.HashMap;
import java.util.Map;

public class UGStructures {

    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Undergarden.MODID);

    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> CATACOMBS = STRUCTURES.register("catacombs", () -> new CatacombsStructure(NoneFeatureConfiguration.CODEC));

    public static final class ConfiguredStructures {
        public static final ConfiguredStructureFeature<?, ?> CATACOMBS = UGStructures.CATACOMBS.get().configured(FeatureConfiguration.NONE);
    }

    public static void registerStructures() {
        setupStructure(CATACOMBS.get(), new StructureFeatureConfiguration(24, 8, 276320045), true);
    }

    public static void registerConfiguredStructures() {
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(Undergarden.MODID, "catacombs"), CATACOMBS.get().configured(FeatureConfiguration.NONE));

        FlatLevelGeneratorSettings.STRUCTURE_FEATURES.put(CATACOMBS.get(), ConfiguredStructures.CATACOMBS);
    }

    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerLevel) {
            ServerLevel level = (ServerLevel)event.getWorld();

            if(level.getChunkSource().getGenerator() instanceof FlatLevelSource && level.dimension().equals(Level.OVERWORLD)) {
                return;
            }

            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(level.getChunkSource().generator.getSettings().structureConfig());
            tempMap.put(UGStructures.CATACOMBS.get(), StructureSettings.DEFAULTS.get(UGStructures.CATACOMBS.get()));
            level.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }

    private static <F extends StructureFeature<?>> void setupStructure(F structure, StructureFeatureConfiguration structureSeparationSettings, boolean transformSurroundingLand) {
        StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        if(transformSurroundingLand) {
            StructureFeature.NOISE_AFFECTING_FEATURES =
                    ImmutableList.<StructureFeature<?>>builder()
                            .addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build();
        }

        StructureSettings.DEFAULTS =
                ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
                        .putAll(StructureSettings.DEFAULTS)
                        .put(structure, structureSeparationSettings)
                        .build();
    }
}