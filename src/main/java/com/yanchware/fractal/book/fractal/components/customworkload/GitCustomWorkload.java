package com.yanchware.fractal.book.fractal.components.customworkload;

import com.yanchware.fractal.book.fractal.Offer;
import com.yanchware.fractal.book.values.Version;
import lombok.Getter;

import java.util.Collection;

@Getter
public abstract class GitCustomWorkload extends Offer {
    private final GitCustomWorkloadConfiguration configuration;

    public GitCustomWorkload(
            Id id,
            Version version,
            String displayName,
            String description,
            Parameters parameters,
            OutputFields outputFields,
            Collection<Link> links,
            Collection<Dependency> dependencies,
            GitCustomWorkloadConfiguration configuration) {
        super(id, version, displayName, description, parameters, outputFields, links, dependencies);
        this.configuration = configuration;
    }

    public record GitCustomWorkloadConfiguration(
                String privateSSHKeyPassphraseSecretId,
                String privateSSHKeySecretId,
                String sshRepositoryURI,
                String repoId,
                String branchName) { }
}
