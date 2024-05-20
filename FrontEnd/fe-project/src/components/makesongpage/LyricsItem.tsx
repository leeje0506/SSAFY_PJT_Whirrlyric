// import './makesongfont/makesong.css';

interface LyricsItemProps {
  lyric: lyrics;
}

export default function LyricsItem({ lyric }: LyricsItemProps) {
  return (
    <div className="mb-4 lyric-list pt-2 w-full">
      <div className="lyric-name">
        {lyric.lyricsName == "Title" ? (
          <div>
            <h1 className="text-3xl font-['Pretendard'] font-extrabold">
              {lyric.lyricsName}
            </h1>
          </div>
        ) : lyric.lyricsName == "Intro" ? (
          <div>
            <h1 className="text-3xl font-['Pretendard'] font-extrabold mb-3">
              {" "}
              Lyrics{" "}
            </h1>
            <h1 className="text-xl font-['Pretendard'] font-extrabold">
              [{lyric.lyricsName}]
            </h1>
          </div>
        ) : (
          <div>
            <h1 className="text-xl font-['Pretendard'] font-extrabold">
              [{lyric.lyricsName}]
            </h1>
          </div>
        )}
        {/*<h1 className="text-xl font-['Pretendard'] font-extrabold">[{lyric.lyricsName}]</h1>*/}
      </div>
      <div className="cursor-pointer lyric-input mt-1">
        <textarea
          className="textbox w-full text-lg py-1 border-2 border-black rounded-lg"
          name={lyric.lyricsName}
        >
          {/*<input type="text"/>*/}
        </textarea>
      </div>
    </div>
  );
}
