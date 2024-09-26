package no.uib.inf101.sample.controller;

import no.uib.inf101.sample.GameView;
import no.uib.inf101.sample.model.ProjectileModel;

public class ProjectileController {

    private ProjectileModel projectile;

    public ProjectileController(ProjectileModel projectile) {
        this.projectile = projectile;
    }

    /**
     * Makes a call to the move-method on the ProjectileModel-object of the instance-variable and sets the return as the new value of the instance-variable.
     * Returns the ProjectileModel-object of the instance-variable.
     *
     * @param delta
     * @return
     */
    public  ProjectileModel moveProjectile(double delta) {
        this.projectile = this.projectile.move(this.projectile.getVelocity());
        return this.projectile;
    }

}
