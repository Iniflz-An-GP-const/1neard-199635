package shejianguo.app1neard.corpapp.di

import shejianguo.app1neard.corpapp.data.datastore.OnboardingDataStoreManager
import shejianguo.app1neard.corpapp.data.datastore.ProfileDataStoreManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { OnboardingDataStoreManager(androidContext()) }
    single { ProfileDataStoreManager(androidContext()) }
}