// import './makesongfont/makesong.css';


interface LyricsItemProps {
    lyric: lyrics;
}

export default function LyricsItem({lyric}: LyricsItemProps){
    return (
        <div className="mb-4 lyric-list pt-2">
            <div className="lyric-name">
            <h1 className="text-2xl">{lyric.lyricsName}</h1>
            </div>
            <div className="cursor-pointer lyric-input mt-1">
                <textarea className="textbox w-full text-lg py-1 border-2 border-black rounded-lg" name={lyric.lyricsName}>
                    {/*<input type="text"/>*/}
                </textarea>
            </div>
        </div>
    );
}