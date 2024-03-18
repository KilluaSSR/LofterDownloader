package  com.killua.mediadownloader.di
import android.content.Context
import com.killua.mediadownloader.ImageDownloader
import com.killua.mediadownloader.ImageSaver
import com.killua.mediadownloader.LofterLoadData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context
}