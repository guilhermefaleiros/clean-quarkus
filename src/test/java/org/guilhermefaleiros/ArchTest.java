package org.guilhermefaleiros;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@QuarkusTest
class ArchTest {

    JavaClasses importedClasses = new ClassFileImporter().importPackages("org.guilhermefaleiros");

    @Test
    public void testDomainShouldNotAccessAnyOtherLayer() {
        ArchRule rule = noClasses().that()
                .resideInAPackage("..domain..")
                .should().accessClassesThat()
                .resideInAnyPackage("..infra..", "..presentation..", "..data..", "..main..");

        rule.check(importedClasses);
    }

    @Test
    public void testInfraShouldOnlyBeAccessedByDataAndMain() {
        ArchRule rule = layeredArchitecture()
                .layer("Infra").definedBy("org.guilhermefaleiros.infra..")
                .layer("Main").definedBy("org.guilhermefaleiros.main..")
                .layer("Domain").definedBy("org.guilhermefaleiros.domain..")
                .layer("Presentation").definedBy("org.guilhermefaleiros.presentation..")
                .layer("Data").definedBy("org.guilhermefaleiros.data..")
                .whereLayer("Infra").mayOnlyBeAccessedByLayers("Main");

        rule.check(importedClasses);
    }

    @Test
    public void testDataShouldOnlyBeAccessedByInfraAndMain() {
        ArchRule rule = layeredArchitecture()
                .layer("Infra").definedBy("org.guilhermefaleiros.infra..")
                .layer("Main").definedBy("org.guilhermefaleiros.main..")
                .layer("Domain").definedBy("org.guilhermefaleiros.domain..")
                .layer("Presentation").definedBy("org.guilhermefaleiros.presentation..")
                .layer("Data").definedBy("org.guilhermefaleiros.data..")
                .whereLayer("Data").mayOnlyBeAccessedByLayers("Main", "Infra");

        rule.check(importedClasses);
    }

    @Test
    public void testMainShouldOnlyBeAccessedByPresentation() {
        ArchRule rule = layeredArchitecture()
                .layer("Infra").definedBy("org.guilhermefaleiros.infra..")
                .layer("Main").definedBy("org.guilhermefaleiros.main..")
                .layer("Domain").definedBy("org.guilhermefaleiros.domain..")
                .layer("Presentation").definedBy("org.guilhermefaleiros.presentation..")
                .layer("Data").definedBy("org.guilhermefaleiros.data..")
                .whereLayer("Main").mayOnlyBeAccessedByLayers("Presentation");

        rule.check(importedClasses);
    }

    @Test
    public void testPresentationShouldNotBeAccessedByAnyLayer() {
        ArchRule rule = layeredArchitecture()
                .layer("Infra").definedBy("org.guilhermefaleiros.infra..")
                .layer("Main").definedBy("org.guilhermefaleiros.main..")
                .layer("Domain").definedBy("org.guilhermefaleiros.domain..")
                .layer("Presentation").definedBy("org.guilhermefaleiros.presentation..")
                .layer("Data").definedBy("org.guilhermefaleiros.data..")
                .whereLayer("Presentation").mayNotBeAccessedByAnyLayer();

        rule.check(importedClasses);
    }
}
