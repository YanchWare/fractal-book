package com.yanchware.fractal.book.livesystem;

import com.yanchware.fractal.book.BoundedContext;
import com.yanchware.fractal.book.ComponentBase;
import com.yanchware.fractal.book.Environment;
import com.yanchware.fractal.book.fractal.BlueprintComponent;
import com.yanchware.fractal.book.fractal.Fractal;
import com.yanchware.fractal.book.fractal.Interface;
import com.yanchware.fractal.book.fractal.Offer;
import com.yanchware.fractal.book.values.KebabCaseString;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class LiveSystem<F extends Fractal<? extends Interface>> {
    private final Id id;
    private final String displayName;
    private final String description;
    private final List<Mutation> mutations;
    private final Environment.Id environmentId;
    private final F fractal;
    private Mutation.Id currentMutationId;
    private Status status;

    public LiveSystem(Id id, String displayName, String description, Environment.Id environmentId, F fractal) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.environmentId = environmentId;
        this.fractal = fractal;
        this.status = Status.ACTIVE;
        this.mutations = new ArrayList<>();
    }

    public record Id(
            BoundedContext.Id boundedContextId,
            KebabCaseString name){ }

    public enum Status {
        ACTIVE,
        MUTATING,
        FAILED
    }

    public record Mutation(
            Id id,
            List<LiveSystemComponent> components,
            Status status)
    {
        public record Id (UUID value) { }

        public enum Status {
            IN_PROGRESS,
            COMPLETED,
            FAILED,
            CANCELLED
        }
    }

    public void instantiate(InstantiationConfiguration instantiationConfiguration) {
        this.status = Status.MUTATING;
        var mutationId = UUID.randomUUID();
        var blueprint = getFractal().getBlueprint();
        var componentList = new ArrayList<LiveSystemComponent>();
        var serviceComponentMap = instantiationConfiguration.serviceMapByComponentId();
        for(var blueprintComponent : blueprint.components()) {
            if (!serviceComponentMap.containsKey(blueprintComponent.getId())) {
                throw new IllegalArgumentException(STR."Missing configuration for blueprint component: \{blueprintComponent.getId()}");
            }
            var offerSelector = serviceComponentMap.get(blueprintComponent.getId());
            componentList.add(
                    blueprintComponent.getServices().stream().filter(
                            s -> s.getType().equals(offerSelector.serviceType)).findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Service specified in selector is not present in the blueprint component"))
                                .getOffers().stream().filter(
                                        o -> o.getType().equals(offerSelector.offerType)).findFirst().orElseThrow(() -> new IllegalArgumentException("Offer specified in selector is not present in the blueprint component"))
                                            .getInstantiatingLiveSystemComponent());
        }

        this.mutations.add(new LiveSystem.Mutation(
                new LiveSystem.Mutation.Id(mutationId),
                componentList,
                LiveSystem.Mutation.Status.IN_PROGRESS
        ));
        this.currentMutationId = new Mutation.Id(mutationId);
    }

    public record InstantiationConfiguration(Map<ComponentBase.Id, OfferSelector> serviceMapByComponentId) {
    }

    public record OfferSelector(BlueprintComponent.Service.Type serviceType, Offer.Type offerType) {
    }
}