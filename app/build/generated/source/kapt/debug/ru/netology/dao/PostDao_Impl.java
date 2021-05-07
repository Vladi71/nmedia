package ru.netology.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import ru.netology.entity.PostEntity;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PostDao_Impl implements PostDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PostEntity> __insertionAdapterOfPostEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateContentById;

  private final SharedSQLiteStatement __preparedStmtOfLikeById;

  private final SharedSQLiteStatement __preparedStmtOfRemoveById;

  public PostDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPostEntity = new EntityInsertionAdapter<PostEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `PostEntity` (`id`,`author`,`authorAvatar`,`content`,`published`,`likedByMe`,`likes`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PostEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getAuthor() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAuthor());
        }
        if (value.getAuthorAvatar() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAuthorAvatar());
        }
        if (value.getContent() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getContent());
        }
        if (value.getPublished() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPublished());
        }
        final int _tmp;
        _tmp = value.getLikedByMe() ? 1 : 0;
        stmt.bindLong(6, _tmp);
        stmt.bindLong(7, value.getLikes());
      }
    };
    this.__preparedStmtOfUpdateContentById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE PostEntity SET content = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfLikeById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "\n"
                + "        UPDATE PostEntity SET\n"
                + "        likes = likes + CASE WHEN likedByMe THEN -1 ELSE 1 END,\n"
                + "        likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END\n"
                + "        WHERE id = ?\n"
                + "        ";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM PostEntity WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final PostEntity post) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPostEntity.insert(post);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateContentById(final long id, final String content) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateContentById.acquire();
    int _argIndex = 1;
    if (content == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, content);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateContentById.release(_stmt);
    }
  }

  @Override
  public void likeById(final long id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfLikeById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfLikeById.release(_stmt);
    }
  }

  @Override
  public void removeById(final long id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveById.release(_stmt);
    }
  }

  @Override
  public LiveData<List<PostEntity>> getAll() {
    final String _sql = "SELECT * FROM PostEntity ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"PostEntity"}, false, new Callable<List<PostEntity>>() {
      @Override
      public List<PostEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfAuthorAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "authorAvatar");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfPublished = CursorUtil.getColumnIndexOrThrow(_cursor, "published");
          final int _cursorIndexOfLikedByMe = CursorUtil.getColumnIndexOrThrow(_cursor, "likedByMe");
          final int _cursorIndexOfLikes = CursorUtil.getColumnIndexOrThrow(_cursor, "likes");
          final List<PostEntity> _result = new ArrayList<PostEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PostEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            final String _tmpAuthorAvatar;
            _tmpAuthorAvatar = _cursor.getString(_cursorIndexOfAuthorAvatar);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final String _tmpPublished;
            _tmpPublished = _cursor.getString(_cursorIndexOfPublished);
            final boolean _tmpLikedByMe;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfLikedByMe);
            _tmpLikedByMe = _tmp != 0;
            final int _tmpLikes;
            _tmpLikes = _cursor.getInt(_cursorIndexOfLikes);
            _item = new PostEntity(_tmpId,_tmpAuthor,_tmpAuthorAvatar,_tmpContent,_tmpPublished,_tmpLikedByMe,_tmpLikes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public void save(final PostEntity post) {
    PostDao.DefaultImpls.save(PostDao_Impl.this, post);
  }
}
