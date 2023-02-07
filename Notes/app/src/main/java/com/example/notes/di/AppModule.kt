package com.example.notes.di

import android.app.Application
import androidx.room.Room
import com.example.notes.data.data_source.NoteDatabase
import com.example.notes.data.data_source.NoteDatabase.Companion.DATABASE_NAME
import com.example.notes.data.repository.NoteRepositoryImpl
import com.example.notes.domain.repository.NoteRepository
import com.example.notes.domain.use_case.DeleteNoteUseCase
import com.example.notes.domain.use_case.GetNotesUseCase
import com.example.notes.domain.use_case.InsertUseCase
import com.example.notes.domain.use_case.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            insertNote = InsertUseCase(repository)
        )
    }

}