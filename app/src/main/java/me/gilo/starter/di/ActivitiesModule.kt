package me.gilo.starter.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.gilo.starter.MainActivity
import me.gilo.starter.ui.StarterActivity
import me.gilo.starter.ui.home.HomeActivity
import me.gilo.starter.ui.note.AddNoteActivity
import me.gilo.starter.ui.user.onboarding.SignInActivity
import me.gilo.starter.ui.user.onboarding.SignUpActivity

/**
 * Created by amrro <amr.elghobary></amr.elghobary>@gmail.com> on 7/22/17.
 *
 *
 * Injects [android.app.Activity]s
 */

@Module
internal abstract class ActivitiesModule {

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributesHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun contributesSignInActivity(): SignInActivity

    @ContributesAndroidInjector
    internal abstract fun contributesSignUpActivity(): SignUpActivity

    @ContributesAndroidInjector
    internal abstract fun contributesAddNoteUpActivity(): AddNoteActivity

}
