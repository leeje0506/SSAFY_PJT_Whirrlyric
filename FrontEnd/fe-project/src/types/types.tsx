// mypage
interface Song {
  createdAt: string;
  modifiedAt: string;
  songId: number;
  title: string;
  genre: string;
  lyrics: string;
  imageUrl: string | null;
  songUrl: string;
  playCount: number;
}

interface SongWithCreator {
  song: Song;
  nickname: string;
}

interface MemberProfile {
  nickname: string;
  imageUrl: string;
  mainSong: SongWithCreator | null;
  songList: SongWithCreator[] | null;
}

// others..